package com.example.applications.activity;

import static com.example.applications.api.ApiConfig.SHOW_ERROR_DELAY;
import static com.hjq.http.EasyUtils.postDelayed;
import static com.umeng.socialize.utils.ContextUtil.getContext;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.applications.R;
import com.example.applications.api.Api;
import com.example.applications.api.ApiConfig;
import com.example.applications.callback.TtitCallback;
import com.example.applications.manager.InputTextManager;
import com.example.applications.other.KeyboardWatcher;
import com.example.applications.util.BaseActivity;
import com.example.applications.util.StringUtils;
import com.hjq.widget.view.CountdownView;
import com.hjq.widget.view.SubmitButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity
        implements KeyboardWatcher.SoftKeyboardStateListener{
    private EditText etAccount;
    private EditText etPassword;
    private EditText etRePassword;
    private EditText etPhone;
    private EditText etVerification;
    private CountdownView etCountdownView;
    private SubmitButton btn_register;
//    private Button btn_login;
    private String flag;
    private Bundle bundle;
    private ViewGroup RegisterBody,btnGroup;
    private ImageView RegisterLogo;
    /** logo 缩放比例 */
    private final float mLogoScale = 0.8f;
    /** 动画时间 */
    private final int mAnimTime = 300;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    navigatetoFinish(LoginActivity.class);
                }break;
                case 1: {
                    etAccount.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
                    btn_register.showError(SHOW_ERROR_DELAY);
                }break;
            }
        }
    };
    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        etRePassword = findViewById(R.id.et_repassword);
        btn_register = findViewById(R.id.btn_register);
        etPhone = findViewById(R.id.et_phone);
        etVerification = findViewById(R.id.et_verification);
        etCountdownView = findViewById(R.id.cv_phone_reset_countdown);



        RegisterBody = findViewById(R.id.register_body);
        RegisterLogo = findViewById(R.id.register_logo);
        btnGroup = findViewById(R.id.btn_group);
    }

    @Override
    protected void initData() {
        postDelayed(()->{
            KeyboardWatcher.with(RegisterActivity.this)
                    .setListener(RegisterActivity.this);
        },500);

        bundle = getExtrasData("bundle");
        // 在Bundle中获取需要的数据
        flag = bundle.getString("flag");

        etCountdownView.setOnClickListener(new OnClick());
        btn_register.setOnClickListener(new OnClick());

        InputTextManager.with(this)
                .addView(etAccount)
                .addView(etPassword)
                .addView(etRePassword)
                .addView(etPhone)
                .addView(etVerification)
                .setMain(btn_register)
                .build();
    }
    private Bundle getExtrasData(String name){
        // 获取传输过来的Intent对象
        Intent intent = getIntent();
        // 检查Intent是否包含额外的数据
        if (intent != null && intent.hasExtra(name)) {
            // 获取传输过来的Bundle对象
            Bundle receivedBundle = intent.getBundleExtra(name);

            // 在Bundle中获取需要的数据
            if (receivedBundle != null) {
                return receivedBundle;
            }

        }
        return null;
    }

    private void register(String phone,String verification,String account,String password,String rePassword){
        if (phone.length() != 11) {
            etPhone.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
            btn_register.showError(SHOW_ERROR_DELAY);
            toast(getString(R.string.common_phone_input_error));
            return;
        }
        if (verification.length() != getResources().getInteger(R.integer.sms_code_length)) {
            etVerification.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
            btn_register.showError(SHOW_ERROR_DELAY);
            toast(getString(R.string.common_code_error_hint));
            return;
        }
        if(StringUtils.isEmpty(account)){
            toast(getString(R.string.toast_empty_account));

            btn_register.showError(SHOW_ERROR_DELAY);
            return;
        }
        if(StringUtils.isEmpty(password)){
            toast(getString(R.string.toast_empty_password));
            etPassword.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
            btn_register.showError(SHOW_ERROR_DELAY);
            return;
        }
        if(StringUtils.isEqual(password,rePassword)){
            toast(getString(R.string.toast_not_match_password));
            etPassword.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
            etRePassword.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
            btn_register.showError(SHOW_ERROR_DELAY);

            return;
        }
//        Bundle bundle = new Bundle();
//        bundle.putString(SQLITE_COLUMN_TOKEN,account);
//        bundle.putString(SQLITE_COLUMN_USER_PHONE,phone);
//        roomSqlite(ROOM_OPERATION_INSERT, bundle, null);

        //HashMap用于存储键值对
        HashMap<String,Object> params = new HashMap<>();
        params.put("mobile",account);
        params.put("password",password);
        Api.config(ApiConfig.REGISTER,params).postRequest(this,new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                // 在 UI 线程更新 UI
                // 处理响应数据，例如更新 UI
                //showToast(res);
                int code=-1;
                try {
                    JSONObject jsonRequest = new JSONObject(res);
                    code = jsonRequest.getInt("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                if (code==0){
                    postDelayed(() -> {
                        btn_register.showSucceed();
                        postDelayed(() -> {
                            navigatetoFinish(LoginActivity.class);
                        }, 1000);
                    }, 2000);
                }else {
                    handler.sendEmptyMessage(1);
                    toast(res);

                }


            }
            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(1);
                toast(getString(R.string.toast_error_network));
            }
        });

    }



    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.cv_phone_reset_countdown){
                if (etPhone.getText().toString().length() != 11) {
                    etPhone.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake_anim));
                    toast(getString(R.string.common_phone_input_error));
                    return;
                }

                if (true) {
                    toast(getString(R.string.common_code_send_hint));
                    etCountdownView.start();
                    return;
                }
//                // 获取验证码
//                EasyHttp.post(this)
//                        .api(new GetCodeApi()
//                                .setPhone(etPhone.getText().toString()))
//                        .request(new HttpCallback<HttpData<Void>>(this) {
//
//                            @Override
//                            public void onSucceed(HttpData<Void> data) {
//                                toast(R.string.common_code_send_hint);
//                                etCountdownView.start();
//                            }
//
//                            @Override
//                            public void onFail(Exception e) {
//                                super.onFail(e);
//                                etCountdownView.start();
//                            }
//                        });
            }else if (id == R.id.btn_register){
                btn_register.showProgress();
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String rePassword = etRePassword.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String verification = etVerification.getText().toString().trim();

                register(phone,verification,account,password,rePassword);
            }
//            else if (id == R.id.btn_login){
//                if (flag.equals("loginActivity")){
//                    finish();
//                }else {
//                    navigatetoFinish(LoginActivity.class);
//                }
//
//            }
        }
    }


    /**
     * {@link KeyboardWatcher.SoftKeyboardStateListener}
     */

    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        // 执行位移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(RegisterBody, "translationY", 0, -(btnGroup.getHeight()/2));
        objectAnimator.setDuration(mAnimTime);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

        // 执行缩小动画
        RegisterLogo.setPivotX(RegisterLogo.getWidth() / 2f);
        RegisterLogo.setPivotY(RegisterLogo.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(RegisterLogo, "scaleX", 1f, mLogoScale);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(RegisterLogo, "scaleY", 1f, mLogoScale);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(RegisterLogo, "translationY", 0f, -(btnGroup.getHeight()/2));
        animatorSet.play(translationY).with(scaleX).with(scaleY);
        animatorSet.setDuration(mAnimTime);
        animatorSet.start();
    }

    @Override
    public void onSoftKeyboardClosed() {
        // 执行位移动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(RegisterBody, "translationY", RegisterBody.getTranslationY(), 0f);
        objectAnimator.setDuration(mAnimTime);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

        if (RegisterLogo.getTranslationY() == 0) {
            return;
        }

        // 执行放大动画
        RegisterLogo.setPivotX(RegisterLogo.getWidth() / 2f);
        RegisterLogo.setPivotY(RegisterLogo.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(RegisterLogo, "scaleX", mLogoScale, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(RegisterLogo, "scaleY", mLogoScale, 1f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(RegisterLogo, "translationY", RegisterLogo.getTranslationY(), 0f);
        animatorSet.play(translationY).with(scaleX).with(scaleY);
        animatorSet.setDuration(mAnimTime);
        animatorSet.start();
    }
//    private void register(String account,String password,String repassword){
//        if(StringUtils.isEmpty(account)){
//            //Toast.makeText(this,"账号不能为空！！",Toast.LENGTH_SHORT).show();
//            showToast("账号不能为空！！");
//            return;
//        }
//        if(StringUtils.isEmpty(password)){
//            //Toast.makeText(this,"密码不能为空！！",Toast.LENGTH_SHORT).show();
//            showToast("密码不能为空！！");
//            return;
//        }
//        if(StringUtils.isInequal(password,repassword)){
//            showToast("两次密码不一致！！");
//            return;
//        }
//
//        JSONObject jsonRequest = new JSONObject();
//        try {
//            jsonRequest.put("mobile", account);
//            jsonRequest.put("password", password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        //创建okhttpclient
//        //创建 OkHttpClient 实例
//        OkHttpClient client = new OkHttpClient();
//
//        // 将 JSONObject 转换为 JSON 字符串
//        String jsonString = jsonRequest.toString();
//
//        // 设置请求体为 JSON 数据
//        RequestBody requestBody = RequestBody.create(jsonString,
//                MediaType.parse("application/json; charset=utf-8"));
//
//
//        // 创建请求对象，指定请求方式为 POST，设置请求体
//        Request request = new Request.Builder()
//                .url(ApiConfig.BASE_URL+ ApiConfig.REGISTER) // 替换为实际的后端 API 地址
//                .addHeader("contentType","application/json; charset=UTF-8")
//                .post(requestBody)
//                .build();
//
//        // 发起异步 POST 请求
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // 网络请求失败时的处理
//                e.printStackTrace();
//                showToast("fail");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                // 网络请求成功时的处理
//                if (response.isSuccessful()) {
//                    final String responseData = response.body().string();
//
//                    // 在 UI 线程更新 UI
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // 处理响应数据，例如更新 UI
////                            TextView textView = findViewById(R.id.textView);
////                            textView.setText(responseData);
//                            //showToast(responseData);
//                            navigateto(login.class,1);
//                        }
//                    });
//                }
//            }
//        });
//
//
//    }
}