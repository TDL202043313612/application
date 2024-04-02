package com.example.applications;

import static com.example.applications.api.ApiConfig.DOUBLE_BACK_PRESS_TIME_INTERVAL;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.applications.activity.HomeActivity;
import com.example.applications.activity.LoginActivity;
import com.example.applications.activity.RegisterActivity;
import com.example.applications.util.BaseActivity;

public class MainActivity extends BaseActivity {
    private Button btnLogin;
    private Button btnRegister;
    private long lastBackPressedTime = 0;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }



    @Override
    protected void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {

        updateNavigationBarColor(R.color.colorPrimary);

        if (getStringFromSp("auto").equals("true")){
            navigatetoFinish(HomeActivity.class);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateto(LoginActivity.class);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flag","mainActivity");
                navigatetoBundle(RegisterActivity.class,bundle);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();


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