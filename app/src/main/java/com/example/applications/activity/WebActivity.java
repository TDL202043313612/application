package com.example.applications.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.applications.R;
import com.example.applications.jsbridge.BridgeHandler;
import com.example.applications.jsbridge.BridgeWebView;
import com.example.applications.jsbridge.CallBackFunction;
import com.example.applications.util.BaseActivity;

public class WebActivity extends BaseActivity {
    private String url;
    private BridgeWebView bridgeWebView;
    @Override
    protected int initLayout() {
        return R.layout.activity_web;
    }



    @Override
    protected void initView() {
        bridgeWebView = findViewById(R.id.bridgeWebView);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            url = bundle.getString("url");
        }
        InitWebView();
    }

    private void InitWebView(){
        /*必须使能，否则不能打开网页*/
        bridgeWebView.getSettings().setJavaScriptEnabled(true);
        bridgeWebView.loadUrl(url);
        /*退回*/
        bridgeWebView.registerHandler("goback", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                finish();
            }
        });
    }
}