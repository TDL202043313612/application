package com.example.applications;

import static com.example.applications.api.ApiConfig.DOUBLE_BACK_PRESS_TIME_INTERVAL;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.applications.util.BaseActivity;

public class SplashActivity extends BaseActivity {
    private TextView buildTimeTv;
    private LottieAnimationView lottieAnimationView;
    private long lastBackPressedTime = 0;

    @Override
    protected int initLayout() {
        return R.layout.activity_splash;
    }



    @Override
    protected void initView() {
//        buildTimeTv = findViewById(R.id.tv_build_time);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initData() {
        updateNavigationBarColor(R.color.colorPrimary);
        // 设置动画监听
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieAnimationView.removeAnimatorListener(this);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        buildTimeTv.setText(String.format(getString(R.string.splash_build_time), "2024-01-25"));
//        buildTimeTv.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 2000);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        // 如果搜索视图是关闭的，则判断是否双击了返回键
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBackPressedTime< DOUBLE_BACK_PRESS_TIME_INTERVAL) {
            // 如果两次按返回键的时间间隔小于预定义的时间间隔，则退出应用程序
            // 关闭整个应用程序
            finishAffinity();
        } else {
            // 否则更新上一次按返回键的时间，并显示提示信息
            lastBackPressedTime = currentTime;
            toast(getString(R.string.toast_back_application));
        }

    }
}