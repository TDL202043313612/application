package com.example.applications.activity;

import static com.example.applications.api.ApiConfig.ROOM_OPERATION_INSERT;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_USER_NAME;
import static com.example.applications.api.ApiConfig.SHOW_ERROR_DELAY;
import static com.example.applications.api.ApiConfig.SHOW_NO_ERROR;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_TOKEN;
import static com.hjq.http.EasyUtils.postDelayed;
import static com.umeng.socialize.utils.ContextUtil.getContext;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.applications.MainActivity;
import com.example.applications.R;
import com.example.applications.aop.SingleClick;
import com.example.applications.api.Api;
import com.example.applications.api.ApiConfig;
import com.example.applications.arcface.InitRecognizeActivity;
import com.example.applications.callback.TtitCallback;
import com.example.applications.entity.LoginResponse;
import com.example.applications.manager.InputTextManager;
import com.example.applications.other.KeyboardWatcher;
import com.example.applications.util.BaseActivity;
import com.example.applications.util.StringUtils;
import com.example.applications.util.TimerManager;
import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.widget.view.SubmitButton;

import java.util.HashMap;

public class LoginActivity extends BaseActivity
        implements KeyboardWatcher.SoftKeyboardStateListener {
    private EditText etAccount;
    private EditText etPassword;
    private SubmitButton btnLogin;
//    private Button btn_register;
    private ProgressBar progressBar;
    private TimerManager timerManager;
    private TitleBar titleBar;
    private TextView forgePassword,agreement,faceRecognition;
    private LinearLayout agreementRe;
    private CheckBox checkBox;
    private ViewGroup loginBody,btnGroup;
    private ImageView loginLogo;


    /** logo 缩放比例 */
    private final float mLogoScale = 0.8f;
    /** 动画时间 */
    private final int mAnimTime = 300;

    private android.os.Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    btnLogin.setEnabled(true);
                    progressBar.setVisibility(View.INVISIBLE);
                    timerManager.stopRepeatingTask();
                    progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
                }
                break;
                case 1: {
                    btnLogin.showError(SHOW_ERROR_DELAY);
                }
                break;
            }
        }
    };


    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
//        btn_register = findViewById(R.id.btn_register);
        titleBar = findViewById(R.id.title_bar);
        forgePassword = findViewById(R.id.tv_login_forget);

        agreementRe = findViewById(R.id.tv_login_agreement_re);
        checkBox = findViewById(R.id.tv_login_check_box);
        agreement = findViewById(R.id.tv_login_agreement);

        loginBody = findViewById(R.id.login_body);
        loginLogo = findViewById(R.id.login_logo);
        btnGroup = findViewById(R.id.btn_group);

        faceRecognition = findViewById(R.id.tx_face_recognition);
    }

    @Override
    protected void initData() {
        postDelayed(() -> {
            KeyboardWatcher.with(LoginActivity.this)
                    .setListener(LoginActivity.this);
        }, 500);
        setOnClickListener(btnLogin,forgePassword,agreementRe,agreement,faceRecognition);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View view) {
                navigatetoFinish(MainActivity.class);

            }

            @Override
            public void onTitleClick(View view) {

            }

            @Override
            public void onRightClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("flag","loginActivity");
                navigatetoBundle(RegisterActivity.class,bundle);
            }


        });

        //文本输入管理类，通过管理多个 EditText 输入是否为空来启用或者禁用按钮的点击事件
        InputTextManager.with(this)
                .addView(etAccount)
                .addView(etPassword)
                .setMain(btnLogin)
                .build();

    }

    private void login(String account,String password){
        if(StringUtils.isEmpty(account)){
            toast(getString(R.string.toast_empty_account));
            etAccount.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
            btnLogin.showError(SHOW_ERROR_DELAY);
            return;
        }
        if(StringUtils.isEmpty(password)){
            toast(getString(R.string.toast_empty_password));
            etPassword.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
            btnLogin.showError(SHOW_ERROR_DELAY);
            return;
        }
        //HashMap用于存储键值对
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("mobile",account);
        params.put("password",password);
        Api.config(ApiConfig.LOGIN,params).postRequest(this,new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                // 在 UI 线程更新 UI
                LoginResponse loginResponse = new Gson().fromJson(res,LoginResponse.class);
                if (loginResponse.getCode()==0){
//                    TipDialog.show("登陆成功", WaitDialog.TYPE.SUCCESS,500)
//                            .setDialogLifecycleCallback(new DialogLifecycleCallback<WaitDialog>() {
//                                @Override
//                                public void onShow(WaitDialog dialog) {
//                                    saveStringToSp("token",loginResponse.getToken());
//                                }
//                                @Override
//                                public void onDismiss(WaitDialog dialog) {
//                                    //对话框关闭时回调
//                                    int flag = Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK;
//                                    navigatetoFlag(HomeActivity.class,flag);
//                                }
//                            });
                    saveStringToSp("token",loginResponse.getToken());
                    saveStringToSp(SHARED_PREFERENCES_USER_NAME,account);
//                    saveStringToSp("user_password",password);
                    saveStringToSp("user_phone","17769443613");


                    Bundle bundle = new Bundle();
                    bundle.putString(SQLITE_COLUMN_TOKEN,account);
                    roomSqlite(ROOM_OPERATION_INSERT,bundle,null);


                    postDelayed(() -> {
                        btnLogin.showSucceed();
                        postDelayed(() -> {
                            int flag = Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK;
                            navigatetoFlag(HomeActivity.class,flag);
                        }, 1000);
                    }, 2000);

                }else {
                    handler.sendEmptyMessage(1);
                    toast(loginResponse.getMsg());
                }

            }
            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(1);
                toast(getString(R.string.toast_error_network));
            }
        });

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login){/*登录*/
            if (checkBox.isChecked()) {
                btnLogin.showProgress();
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                login(account, password);
            }else {
                agreementRe.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
                btnLogin.showError(SHOW_NO_ERROR);
            }
        }else if (id == R.id.tv_login_forget){/*忘记密码*/
            navigateto(PasswordForgetActivity.class);
        }else if(id == R.id.tv_login_agreement_re){/*是否同意隐私协议*/
            checkBox.setChecked(!checkBox.isChecked());
        }else if(id == R.id.tv_login_agreement){/*打开隐私协议*/
            navigateto(PrivacyAgreementActivity.class);
        }else if (id == R.id.tx_face_recognition){/*人脸识别*/
            if (checkBox.isChecked()) {
                navigateto(InitRecognizeActivity.class);
            }else {
                agreementRe.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
                btnLogin.showError(SHOW_NO_ERROR);
            }

        }
    }







    /**
     * {@link KeyboardWatcher.SoftKeyboardStateListener}
     */

    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        // 执行位移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(loginBody, "translationY", 0, -btnGroup.getHeight());
        objectAnimator.setDuration(mAnimTime);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

        // 执行缩小动画
        loginLogo.setPivotX(loginLogo.getWidth() / 2f);
        loginLogo.setPivotY(loginLogo.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(loginLogo, "scaleX", 1f, mLogoScale);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(loginLogo, "scaleY", 1f, mLogoScale);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(loginLogo, "translationY", 0f, -btnGroup.getHeight());
        animatorSet.play(translationY).with(scaleX).with(scaleY);
        animatorSet.setDuration(mAnimTime);
        animatorSet.start();
    }

    @Override
    public void onSoftKeyboardClosed() {
        // 执行位移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(loginBody, "translationY", loginBody.getTranslationY(), 0f);
        objectAnimator.setDuration(mAnimTime);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

        if (loginLogo.getTranslationY() == 0) {
            return;
        }

        // 执行放大动画
        loginLogo.setPivotX(loginLogo.getWidth() / 2f);
        loginLogo.setPivotY(loginLogo.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(loginLogo, "scaleX", mLogoScale, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(loginLogo, "scaleY", mLogoScale, 1f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(loginLogo, "translationY", loginLogo.getTranslationY(), 0f);
        animatorSet.play(translationY).with(scaleX).with(scaleY);
        animatorSet.setDuration(mAnimTime);
        animatorSet.start();
    }


}