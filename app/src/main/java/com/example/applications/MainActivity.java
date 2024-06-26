package com.example.applications;

import static com.example.applications.api.ApiConfig.DOUBLE_BACK_PRESS_TIME_INTERVAL;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.applications.activity.HomeActivity;
import com.example.applications.activity.LoginActivity;
import com.example.applications.util.BaseActivity;
import com.example.applications.util.SkinStatusBarUtils;

import skin.support.content.res.SkinCompatResources;

public class MainActivity extends BaseActivity {
    private LottieAnimationView btnLogin;
//    private Button btnRegister;
    private long lastBackPressedTime = 0;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }



    @Override
    protected void initView() {
        btnLogin = findViewById(R.id.btn_login);
//        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {

        updateNavigationBarColor(R.color.main_background_navigation_bar_color);
        getWindow().setStatusBarColor(SkinCompatResources.getColor(this, R.color.main_background_status_bar_color));
        SkinStatusBarUtils.setStatusBarLightMode(this);
        setTextViewStyles(findViewById(R.id.txt_one_slogan));
        setTextViewStyles(findViewById(R.id.txt_two_slogan));
        setTextViewStyles(findViewById(R.id.txt_three_slogan));

        if (getStringFromSp("auto").equals("true")){
            navigatetoFinish(HomeActivity.class);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateto(LoginActivity.class);
            }
        });
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("flag","mainActivity");
//                navigatetoBundle(RegisterActivity.class,bundle);
//            }
//        });
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

    private void setTextViewStyles(TextView textView) {
        float x1=textView.getPaint().measureText(textView.getText().toString());//测量文本 宽度
        float y1=textView.getPaint().getTextSize();//测量文本 高度
        int c1= getResources().getColor(R.color.main_txt_view_slogan_start_color);//初始颜色值
        int c2= getResources().getColor(R.color.main_txt_view_slogan_end_color);//结束颜色值

        LinearGradient leftToRightLG = new LinearGradient(0, 0, x1, 0,c1, c2, Shader.TileMode.CLAMP);//从左到右渐变
        LinearGradient topToBottomLG = new LinearGradient(0, 0, 0, y1,c1, c2, Shader.TileMode.CLAMP);//从上到下渐变

        textView.getPaint().setShader(leftToRightLG);
        textView.invalidate();
    }
}