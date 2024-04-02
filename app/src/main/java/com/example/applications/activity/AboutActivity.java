package com.example.applications.activity;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.example.applications.R;
import com.example.applications.aop.SingleClick;
import com.example.applications.app.AppActivity;
import com.hjq.bar.TitleBar;
import com.hjq.widget.layout.SettingBar;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 关于界面
 */
public final class AboutActivity extends AppActivity {
    private TitleBar titleBar;
    private SettingBar abEmail,abPhone;
    private TextView emailTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.about_activity;
    }

    @Override
    protected void initView() {
        titleBar = findViewById(R.id.titleBar);
        abEmail = findViewById(R.id.ab_email);
        abPhone = findViewById(R.id.ab_phone);
        emailTitle = findViewById(R.id.email_title);

    }

    @Override
    protected void initData() {
        setOnClickListener(R.id.ab_email,R.id.ab_phone);

        String combinedString = getString(R.string.about_title, getString(R.string.about), getString(R.string.app_name));
        String combinedString2 = getString(R.string.about_email, getString(R.string.app_name));
        emailTitle.setText(combinedString2);
        titleBar.setTitle(combinedString);

    }
    private void copyText(String text){
        // 获取系统的剪贴板管理器
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 要复制的文本内容
        String textToCopy = text;
        // 创建一个 ClipData 对象，将要复制的文本内容放入其中
        ClipData clip = ClipData.newPlainText("text", textToCopy);
        // 将 ClipData 对象放入剪贴板
        clipboard.setPrimaryClip(clip);
    }
    private void toDial(String phone){
        // 创建 Intent 对象，跳转到拨号页面
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }
    @SingleClick
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.ab_email){
            String email = abEmail.getLeftText().toString().trim();
            copyText(email);
            toast(getString(R.string.toast_success_copy));
        }else if (viewId == R.id.ab_phone){
            String phoneNumber = abPhone.getLeftText().toString().trim();
            toDial(phoneNumber);
        }
    }
}