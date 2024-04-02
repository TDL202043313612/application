package com.example.applications.activity;

import static android.view.View.VISIBLE;
import static com.example.applications.api.ApiConfig.ROOM_OPERATION_QUERY;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_USER_NAME;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_TOKEN;
import static com.example.applications.util.AssessPasswordStrength.assessPasswordStrength;
import static com.example.applications.util.StringUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.applications.R;
import com.example.applications.aop.SingleClick;
import com.example.applications.app.AppActivity;
import com.example.applications.dialog.LatestVersionDialog;
import com.example.applications.dialog.MenuDialog;
import com.example.applications.dialog.MessageDialog;
import com.example.applications.dialog.SafeDialog;
import com.example.applications.dialog.SafeDialog2;
import com.example.applications.dialog.UpdateDialog;
import com.example.applications.http.api.LogoutApi;
import com.example.applications.http.glide.GlideApp;
import com.example.applications.http.model.HttpData;
import com.example.applications.listener.OnAsyncTaskListener;
import com.example.applications.manager.ActivityManager;
import com.example.applications.manager.CacheDataManager;
import com.example.applications.manager.ThreadPoolManager;
import com.example.applications.other.AppConfig;
import com.example.applications.room.PersonalMassageEntity;
import com.hjq.base.BaseDialog;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.language.LocaleContract;
import com.hjq.language.MultiLanguages;
import com.hjq.shape.view.ShapeTextView;
import com.hjq.widget.layout.SettingBar;
import com.hjq.widget.view.SwitchButton;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/03/01
 *    desc   : 设置界面
 */
public final class SettingActivity extends AppActivity
        implements SwitchButton.OnCheckedChangeListener {

    private SettingBar mLanguageView;
    private SettingBar mPhoneView;
    private SettingBar mPasswordView;
    private SettingBar mCleanCacheView;
    private SettingBar mSettingAboutView;
    private SettingBar mFaceLoginView;
    private SwitchButton mAutoSwitchView;

    private ShapeTextView mShapeTextView;

    private final float latestAppVersionCode=2;
    @Override
    protected int getLayoutId() {
        return R.layout.setting_activity;
    }

    @Override
    protected void initView() {
        mLanguageView = findViewById(R.id.sb_setting_language);
        mPhoneView = findViewById(R.id.sb_setting_phone);
        mPasswordView = findViewById(R.id.sb_setting_password);
        mCleanCacheView = findViewById(R.id.sb_setting_cache);
        mAutoSwitchView = findViewById(R.id.sb_setting_switch);
        mSettingAboutView = findViewById(R.id.sb_setting_about);
        mShapeTextView = findViewById(R.id.shapeTextView);
        mFaceLoginView = findViewById(R.id.sb_setting_face_login);
        // 设置切换按钮的监听
        mAutoSwitchView.setOnCheckedChangeListener(this);

        setOnClickListener(R.id.sb_setting_language, R.id.sb_setting_update, R.id.sb_setting_phone,
                R.id.sb_setting_password, R.id.sb_setting_agreement, R.id.sb_setting_about,
                R.id.sb_setting_cache, R.id.sb_setting_auto, R.id.sb_setting_exit,R.id.sb_setting_face_login);

        String combinedString = getString(R.string.about_title, getString(R.string.about), getString(R.string.app_name));
        mSettingAboutView.setLeftText(combinedString);
    }

    @Override
    protected void initData() {
        // 获取应用缓存大小
        getCurrentCacheSize();
        /*获取手机号*/
        getPhoneNumber();
        /*密码强度*/
        passwordStrength();
        /*自动登陆*/
        autoLogin();
        /*是否显示NEW图标*/
        whetherUpdate();
        /*当前语言*/
        currentLanguage();

    }

    @SingleClick
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.sb_setting_language) {

            // 底部选择框
            new MenuDialog.Builder(this)
                    // 设置点击按钮后不关闭对话框
                    //.setAutoDismiss(false)
                    .setList(R.string.setting_language_system,R.string.setting_language_simple, R.string.setting_language_complex,R.string.setting_language_english)
                    .setListener((MenuDialog.OnListener<String>) (dialog, position, string) -> {
                        boolean restart = false;
                        switch (position){
                            //更随系统语种
                            case 0:
                                restart = MultiLanguages.setAppLanguage(this,MultiLanguages.getSystemLanguage(this));
                                MultiLanguages.clearAppLanguage(this);
                                break;
                            // 简体中文
                            case 1:
                                restart = MultiLanguages.setAppLanguage(this, LocaleContract.getSimplifiedChineseLocale());
                                break;
                            // 繁体中文
                            case 2:
                                restart = MultiLanguages.setAppLanguage(this, LocaleContract.getTraditionalChineseLocale());
                                break;
                            //英文
                            case 3:
                                restart = MultiLanguages.setAppLanguage(this, LocaleContract.getEnglishLocale());
                                break;
                        }
//                        toast(restart);
                        if (restart){
                            saveStringToSp("language",string);
                        }


//                        BrowserActivity.start(getActivity(), "https://github.com/getActivity/MultiLanguages");
                    })
                    .setGravity(Gravity.BOTTOM)
                    .setAnimStyle(BaseDialog.ANIM_BOTTOM)
                    .show();

        } else if (viewId == R.id.sb_setting_update) {

            // 本地的版本码和服务器的进行比较
            if (latestAppVersionCode > AppConfig.getVersionCode()) {
                new UpdateDialog.Builder(this)
                        .setVersionName(Float.toString(latestAppVersionCode))
                        .setForceUpdate(false)
                        .setUpdateLog(getString(R.string.setting_update_content))
                        .setDownloadUrl("https://down.qq.com/qqweb/QQ_1/android_apk/Android_8.5.0.5025_537066738.apk")
                        .setFileMd5("560017dc94e8f9b65f4ca997c7feb326")
                        .show();
            } else {
                new LatestVersionDialog.Builder(this).show();
            }

        } else if (viewId == R.id.sb_setting_phone) {
            if (!isEmpty(mPhoneView.getRightText().toString())){
//                new SafeDialog.Builder(this)
//                        .setPhoneNumber(mPhoneView.getRightText().toString())
//                        .setListener((dialog, phone, code) -> PhoneResetActivity.start(getActivity(), code))
//                        .show();
                new SafeDialog.Builder(this)
                        .setPhoneNumber(mPhoneView.getRightText().toString())
                        .setListener((dialog, phone, code) -> startActivityForResult(PhoneResetActivity.getStartIntent(this), new OnActivityCallback() {
                            @Override
                            public void onActivityResult(int resultCode, @Nullable Intent data) {
                                Bundle bundle = new Bundle();
                                bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
                                roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
                                    @Override
                                    public void onTaskCompleted(PersonalMassageEntity result) {
                                        if (result==null)return;
                                        mPhoneView.setRightText(String.format("%s****%s", result.user_phone.substring(0, 3), result.user_phone.substring(result.user_phone.length() - 4)));

                                    }
                                });
                            }
                        }))
                        .show();
            } else{
                // 使用getStartIntent获取Intent，并使用startActivityForResult启动PhoneResetActivity
                Intent intent = PhoneResetActivity.getStartIntent(this);
                startActivityForResult(intent, new OnActivityCallback() {
                    @Override
                    public void onActivityResult(int resultCode, @Nullable Intent data) {

                        Bundle bundle = new Bundle();
                        bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
                        roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
                            @Override
                            public void onTaskCompleted(PersonalMassageEntity result) {
                                if (result==null)return;
                                if (!isEmpty(result.user_phone)) {
                                    mPhoneView.setRightText(String.format("%s****%s", result.user_phone.substring(0, 3), result.user_phone.substring(result.user_phone.length() - 4)));
                                }
                            }
                        });
                    }
                });
            }

        } else if (viewId == R.id.sb_setting_password) {
            if (!isEmpty(mPhoneView.getRightText().toString())){
                new SafeDialog.Builder(this)
                        .setPhoneNumber(mPhoneView.getRightText().toString())
                        .setListener((dialog, phone, code) -> PasswordResetActivity.start(getActivity(), phone, code))
                        .show();
            }else {
                new SafeDialog2.Builder(this)
                        .setListener((dialog) -> PasswordResetActivity.start(getActivity(), "", ""))
                        .show();
            }


        } else if (viewId == R.id.sb_setting_face_login){

            startActivity(SettingFaceLoginActivity.class);

        }else if (viewId == R.id.sb_setting_agreement) {

//            BrowserActivity.start(this, "https://github.com/getActivity/Donate");
            startActivity(PrivacyAgreementActivity.class);
        } else if (viewId == R.id.sb_setting_about) {

            startActivity(AboutActivity.class);

        } else if (viewId == R.id.sb_setting_auto) {

            // 自动登录
            mAutoSwitchView.setChecked(!mAutoSwitchView.isChecked());

        } else if (viewId == R.id.sb_setting_cache) {
            if (CacheDataManager.getTotalCacheSize(this).equals("0K")){
                return;
            }

            // 消息对话框
            new MessageDialog.Builder(getActivity())
                    // 标题可以不用填写
                    .setTitle(R.string.message_dialog_clear_title)
                    // 内容必须要填写
                    .setMessage(R.string.message_dialog_clear_content)
                    // 确定按钮文本
                    .setConfirm(getString(R.string.common_confirm))
                    // 设置 null 表示不显示取消按钮
                    .setCancel(getString(R.string.common_cancel))
                    .setListener(new MessageDialog.OnListener() {
                        @Override
                        public void onConfirm(BaseDialog dialog) {
                            // 清除内存缓存（必须在主线程）
                            GlideApp.get(getActivity()).clearMemory();
                            ThreadPoolManager.getInstance().execute(() -> {
                                CacheDataManager.clearAllCache(getContext());
                                // 清除本地缓存（必须在子线程）
                                GlideApp.get(getActivity()).clearDiskCache();
                                post(() -> {
                                    // 重新获取应用缓存大小
                                    mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(getActivity()));
                                });
                            });
                        }

                        @Override
                        public void onCancel(BaseDialog dialog) {

                        }
                    })
                    .show();


        } else if (viewId == R.id.sb_setting_exit) {

            if (true) {
                layoutLogout();
                return;
            }

            // 退出登录
            EasyHttp.post(this)
                    .api(new LogoutApi())
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            layoutLogout();
                        }
                    });

        }
    }
    private void whetherUpdate(){
        if (latestAppVersionCode > AppConfig.getVersionCode()){
            mShapeTextView.setVisibility(VISIBLE);
        }
    }
    private void getCurrentCacheSize(){
        mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
    }
    private void getPhoneNumber(){
        Bundle bundle = new Bundle();
        bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
        roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
            @Override
            public void onTaskCompleted(PersonalMassageEntity result) {
                if (!isEmpty(result.user_phone)){
                    mPhoneView.setRightText(String.format("%s****%s", result.user_phone.substring(0, 3), result.user_phone.substring(result.user_phone.length() - 4)));

                }
            }
        });

    }
    private void currentLanguage(){
        String language = getStringFromSp("language");
        if (!language.equals("")){
            if (language.equals("英文") || language.equals("英語"))
                mLanguageView.setRightText("English");
            else if (language.equals("Simplified Chinese") || language.equals("簡體中文"))
                mLanguageView.setRightText("简体中文");
            else if (language.equals("Traditional Chinese") || language.equals("繁体中文"))
                mLanguageView.setRightText("繁体中文");
            else mLanguageView.setRightText(R.string.setting_language_system);
        } else{
            mLanguageView.setRightText(R.string.setting_language_simple);
        }
    }
    private void autoLogin(){
        if (getStringFromSp("auto").equals("true")){
            mAutoSwitchView.setChecked(true);
        }else {
            mAutoSwitchView.setChecked(false);
        }
    }
    private void passwordStrength(){
        String password_strength=null;
        switch (assessPasswordStrength(getStringFromSp("user_password"))){
            case WEAK: password_strength = getString(R.string.strength_weak);break;
            case MEDIUM:password_strength = getString(R.string.strength_medium);break;
            case STRONG:password_strength = getString(R.string.strength_strong);break;
        }
        String combinedString = getString(R.string.password_strength,getString(R.string.password_text),password_strength);
        mPasswordView.setRightText(combinedString);
    }
    private void layoutLogout(){
        /*这里把token清0会导致人脸识别进入后没有token值，而闪退*/
//        saveStringToSp("token","");
        saveStringToSp("auto","false");
        startActivity(LoginActivity.class);
        // 进行内存优化，销毁除登录页之外的所有界面
        ActivityManager.getInstance().finishAllActivities(LoginActivity.class);
    }

    /**
     * {@link SwitchButton.OnCheckedChangeListener}
     */

    @Override
    public void onCheckedChanged(SwitchButton button, boolean checked) {
        if (checked){/*自动登录*/
            saveStringToSp("auto","true");
        }else {/*取消自动登录*/
            saveStringToSp("auto","false");
        }
    }
}