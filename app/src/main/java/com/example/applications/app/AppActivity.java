package com.example.applications.app;

import static com.example.applications.api.ApiConfig.ROOM_OPERATION_DELETE;
import static com.example.applications.api.ApiConfig.ROOM_OPERATION_INSERT;
import static com.example.applications.api.ApiConfig.ROOM_OPERATION_QUERY;
import static com.example.applications.api.ApiConfig.ROOM_OPERATION_UPDATE;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_AVATAR_PATH;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_TOKEN;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_USER_ADDRESS;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_USER_LANGUAGE;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_USER_LEAVE_MESSAGE;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_USER_PHONE;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_USER_SKIN;
import static com.example.applications.app.AppApplication.db;
import static com.example.applications.util.StringUtils.isEmpty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.example.applications.R;
import com.example.applications.action.TitleBarAction;
import com.example.applications.action.ToastAction;
import com.example.applications.http.model.HttpData;
import com.example.applications.listener.OnAsyncTaskListener;
import com.example.applications.room.PersonalMassageEntity;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.hjq.base.BaseActivity;
import com.hjq.base.BaseDialog;
import com.hjq.http.listener.OnHttpListener;

import okhttp3.Call;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : Activity 业务基类
 */
public abstract class AppActivity extends BaseActivity
        implements ToastAction,  TitleBarAction, OnHttpListener<Object> {

    /** 标题栏对象 */
    private TitleBar mTitleBar;
    /** 状态栏沉浸 */
    private ImmersionBar mImmersionBar;

    /** 加载对话框 */
    private BaseDialog mDialog;
    /** 对话框数量 */
    private int mDialogCount;

    /**
     * 当前加载对话框是否在显示中
     */
    public boolean isShowDialog() {
        return mDialog != null && mDialog.isShowing();
    }

    /**
     * 显示加载对话框
     */
    public void showDialog() {
//        if (isFinishing() || isDestroyed()) {
//            return;
//        }
//
//        mDialogCount++;
//        postDelayed(() -> {
//            if (mDialogCount <= 0 || isFinishing() || isDestroyed()) {
//                return;
//            }
//
//            if (mDialog == null) {
//                mDialog = new WaitDialog.Builder(this)
//                        .setCancelable(false)
//                        .create();
//            }
//            if (!mDialog.isShowing()) {
//                mDialog.show();
//            }
//        }, 300);
    }

    /**
     * 隐藏加载对话框
     */
    public void hideDialog() {
        if (isFinishing() || isDestroyed()) {
            return;
        }

        if (mDialogCount > 0) {
            mDialogCount--;
        }

        if (mDialogCount != 0 || mDialog == null || !mDialog.isShowing()) {
            return;
        }

        mDialog.dismiss();
    }

    @Override
    protected void initLayout() {
        super.initLayout();

        if (getTitleBar() != null) {
            getTitleBar().setOnTitleBarListener(this);
        }

        // 初始化沉浸式状态栏
        if (isStatusBarEnabled()) {
            getStatusBarConfig().init();

            // 设置标题栏沉浸
            if (getTitleBar() != null) {
                ImmersionBar.setTitleBar(this, getTitleBar());
            }
        }
    }

    public void saveStringToSp(String key,String data){
        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        sp.edit().putString(key,data)
                .commit();
    }
    public String getStringFromSp(String key){
        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        return sp.getString(key,"");
    }
    public String getStringFromSp(String key,String defaultValue){
        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        return sp.getString(key,defaultValue);
    }
    public void removeSp(String key){
        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        sp.edit().remove(key)
                .commit();
    }

    public void roomSqlite(int operationType, Bundle bundle, OnAsyncTaskListener listener){
        PersonalMassageEntity personalMassageEntity = new PersonalMassageEntity();
        if (!isEmpty(bundle.getString(SQLITE_COLUMN_TOKEN,""))){
            personalMassageEntity.token = bundle.getString(SQLITE_COLUMN_TOKEN,"");

        }
        if (!isEmpty(bundle.getString(SQLITE_COLUMN_USER_ADDRESS,""))){
            personalMassageEntity.user_address = bundle.getString(SQLITE_COLUMN_USER_ADDRESS,"");

        }
        if (!isEmpty(bundle.getString(SQLITE_COLUMN_AVATAR_PATH,""))){
            personalMassageEntity.avatar_path = bundle.getString(SQLITE_COLUMN_AVATAR_PATH,"");

        }
        if (!isEmpty(bundle.getString(SQLITE_COLUMN_USER_LANGUAGE,""))){
            personalMassageEntity.user_language = bundle.getString(SQLITE_COLUMN_USER_LANGUAGE,"");

        }
        if (!isEmpty(bundle.getString(SQLITE_COLUMN_USER_PHONE,""))){
            personalMassageEntity.user_phone = bundle.getString(SQLITE_COLUMN_USER_PHONE,"");

        }
        if (!isEmpty(bundle.getString(SQLITE_COLUMN_USER_SKIN,""))){
            personalMassageEntity.user_skin = bundle.getString(SQLITE_COLUMN_USER_SKIN,"");

        }
        if (!isEmpty(bundle.getString(SQLITE_COLUMN_USER_LEAVE_MESSAGE,""))){
            personalMassageEntity.user_leave_message = bundle.getString(SQLITE_COLUMN_USER_LEAVE_MESSAGE,"");

        }

        asyncTask(operationType,personalMassageEntity,listener);

    }
    public void roomSqlite(int operationType, PersonalMassageEntity personalMassageEntity, OnAsyncTaskListener listener){
        asyncTask(operationType,personalMassageEntity,listener);

    }
    public void asyncTask(int operationType,PersonalMassageEntity personalMassageEntity,OnAsyncTaskListener listener){

        new AsyncTask<Void, Void, PersonalMassageEntity>() {
            @Override
            protected PersonalMassageEntity doInBackground(Void... voids) {

                switch (operationType){
                    /*1：插入数据*/
                    case ROOM_OPERATION_INSERT: db.roomTestDao().insert(personalMassageEntity);break;
                    /*2：删除数据*/
                    case ROOM_OPERATION_DELETE: db.roomTestDao().delete(personalMassageEntity);break;
                    /*3：更新数据*/
                    case ROOM_OPERATION_UPDATE: db.roomTestDao().update(personalMassageEntity);break;
                    /*4：查询数据*/
                    case ROOM_OPERATION_QUERY: return db.roomTestDao().getByToken(personalMassageEntity.token);
                }
                return null;
            }
            @Override
            protected void onPostExecute(PersonalMassageEntity result) {
                if (listener != null) {
                    listener.onTaskCompleted(result);
                }
            }
        }.execute();

    }
    /**
     * 是否使用沉浸式状态栏
     */
    protected boolean isStatusBarEnabled() {
        return true;
    }

    /**
     * 状态栏字体深色模式
     */
    protected boolean isStatusBarDarkFont() {
        return true;
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    @NonNull
    public ImmersionBar getStatusBarConfig() {
        if (mImmersionBar == null) {
            mImmersionBar = createStatusBarConfig();
        }
        return mImmersionBar;
    }

    /**
     * 初始化沉浸式状态栏
     */
    @NonNull
    protected ImmersionBar createStatusBarConfig() {
        return ImmersionBar.with(this)
                // 默认状态栏字体颜色为黑色
                .statusBarDarkFont(isStatusBarDarkFont())
                // 指定导航栏背景颜色
                .navigationBarColor(R.color.white)
                // 状态栏字体和导航栏内容自动变色，必须指定状态栏颜色和导航栏颜色才可以自动变色
                .autoDarkModeEnable(true, 0.2f);
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(@StringRes int id) {
        setTitle(getString(id));
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (getTitleBar() != null) {
            getTitleBar().setTitle(title);
        }
    }

    @Override
    @Nullable
    public TitleBar getTitleBar() {
        if (mTitleBar == null) {
            mTitleBar = obtainTitleBar(getContentView());
        }
        return mTitleBar;
    }

    @Override
    public void onLeftClick(View view) {
        onBackPressed();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.right_in_activity, R.anim.right_out_activity);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity);
    }

    /**
     * {@link OnHttpListener}
     */

    @Override
    public void onStart(Call call) {
        showDialog();
    }

    @Override
    public void onSucceed(Object result) {
        if (result instanceof HttpData) {
            toast(((HttpData<?>) result).getMessage());
        }
    }

    @Override
    public void onFail(Exception e) {
        toast(e.getMessage());
    }

    @Override
    public void onEnd(Call call) {
        hideDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isShowDialog()) {
            hideDialog();
        }
        mDialog = null;
    }
}