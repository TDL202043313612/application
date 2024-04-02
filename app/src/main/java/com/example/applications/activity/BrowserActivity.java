package com.example.applications.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.example.applications.R;
import com.example.applications.action.StatusAction;
import com.example.applications.aop.CheckNet;
import com.example.applications.aop.Log;
import com.example.applications.app.AppActivity;
import com.example.applications.widget.BrowserView;
import com.example.applications.widget.StatusLayout;
import com.hjq.language.MultiLanguages;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.Map;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 浏览器界面
 */
public final class BrowserActivity extends AppActivity
        implements StatusAction, OnRefreshListener {

    private static final String INTENT_KEY_IN_URL = "url";

    @CheckNet
    @Log
    public static void start(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(INTENT_KEY_IN_URL, url);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    private StatusLayout mStatusLayout;
    private ProgressBar mProgressBar;
    private SmartRefreshLayout mRefreshLayout;
    private BrowserView mBrowserView;

    @Override
    protected int getLayoutId() {
        return R.layout.browser_activity;
    }

    @Override
    protected void initView() {
        mStatusLayout = findViewById(R.id.hl_browser_hint);
        mProgressBar = findViewById(R.id.pb_browser_progress);
        mRefreshLayout = findViewById(R.id.sl_browser_refresh);
        mBrowserView = findViewById(R.id.wv_browser_view);

        // 设置 WebView 生命管控
        mBrowserView.setLifecycleOwner(this);
        // 设置网页刷新监听
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        showLoading();

        mBrowserView.setBrowserViewClient(new AppBrowserViewClient());
        mBrowserView.setBrowserChromeClient(new AppBrowserChromeClient(mBrowserView));
        mBrowserView.getSettings().setJavaScriptEnabled(true);

//        mBrowserView.setWebViewClient(new LanguagesViewClient());
//        mBrowserView.setWebChromeClient(new WebChromeClient());
        mBrowserView.loadUrl(getString(INTENT_KEY_IN_URL),generateLanguageRequestHeader(this));
    }

    @Override
    public StatusLayout getStatusLayout() {
        return mStatusLayout;
    }

    @Override
    public void onLeftClick(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mBrowserView.canGoBack()) {
            // 后退网页并且拦截该事件
            mBrowserView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 重新加载当前页
     */
    @CheckNet
    private void reload() {
        mBrowserView.reload();
    }

    /**
     * {@link OnRefreshListener}
     */

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        reload();
    }

    private class AppBrowserViewClient extends BrowserView.BrowserViewClient {

        /**
         * 网页加载错误时回调，这个方法会在 onPageFinished 之前调用
         */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // 这里为什么要用延迟呢？因为加载出错之后会先调用 onReceivedError 再调用 onPageFinished
            post(() -> showError(listener -> reload()));
        }

        /**
         * 开始加载网页
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        /**
         * 完成加载网页
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
            mRefreshLayout.finishRefresh();
            showComplete();
        }
    }

    private class AppBrowserChromeClient extends BrowserView.BrowserChromeClient {

        private AppBrowserChromeClient(BrowserView view) {
            super(view);
        }

        /**
         * 收到网页标题
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title == null) {
                return;
            }
            setTitle(title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            if (icon == null) {
                return;
            }
            setRightIcon(new BitmapDrawable(getResources(), icon));
        }

        /**
         * 收到加载进度变化
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
        }
    }
    /**
     * 给 WebView 请求头添加语种环境
     */
    @NonNull
    public static Map<String, String> generateLanguageRequestHeader(Context context) {
        Map<String, String> map = new HashMap<>(1);
        // Android 13 上面语种失效的问题解决方案
        // https://developer.android.google.cn/about/versions/13/features/app-languages?hl=zh-cn#consider-header
        map.put("Accept-Language", String.valueOf(MultiLanguages.getAppLanguage(context)));
        return map;
    }

    public static class LanguagesViewClient extends WebViewClient {

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl().toString());
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String scheme = Uri.parse(url).getScheme();
            if (scheme == null) {
                return false;
            }
            switch (scheme) {
                // 如果这是跳链接操作
                case "http":
                case "https":
                    view.loadUrl(url, generateLanguageRequestHeader(view.getContext()));
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}