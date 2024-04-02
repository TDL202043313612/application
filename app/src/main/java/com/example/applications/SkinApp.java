package com.example.applications;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.applications.api.ApiConfig;
import com.qweather.sdk.view.HeConfig;

import skin.support.BuildConfig;
import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;
import skin.support.flycotablayout.app.SkinFlycoTabLayoutInflater;
import skin.support.utils.Slog;

public class SkinApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HeConfig.init(ApiConfig.PUBLIC_ID,ApiConfig.KEY);
        //切换至免费订阅
        HeConfig.switchToDevService();

        Slog.DEBUG = BuildConfig.DEBUG;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SkinCompatManager.withoutActivity(this)
                .addInflater(new SkinAppCompatViewInflater())   // 基础控件换肤
                .addInflater(new SkinFlycoTabLayoutInflater()) // H07000223/FlycoTabLayout
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();
    }

}
