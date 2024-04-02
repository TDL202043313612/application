package com.example.applications.util;

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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;

import com.dueeeke.videoplayer.player.VideoViewManager;
import com.example.applications.R;
import com.example.applications.action.ToastAction;
import com.example.applications.listener.OnAsyncTaskListener;
import com.example.applications.room.PersonalMassageEntity;
import com.hjq.base.action.ClickAction;
import com.hjq.language.MultiLanguages;

import skin.support.content.res.SkinCompatResources;


public abstract class  BaseActivity extends AppCompatActivity implements ToastAction, ClickAction {
    public Context myContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        setContentView(initLayout());
        updateStatusBarColor();
        initView();
        initData();

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        // 绑定语种
        super.attachBaseContext(MultiLanguages.attach(newBase));
    }

    public void navigateto(Class cls){
        Intent in = new Intent(myContext,cls);
        startActivity(in);
    }
    public void navigatetoFinish(Class cls){
        finish();
        Intent in = new Intent(myContext,cls);
        startActivity(in);
    }
    public void navigatetoFlag(Class cls,int flag){
        Intent in = new Intent(myContext,cls);
        in.setFlags(flag);
        startActivity(in);
    }
    public void navigatetoBundle(Class cls,Bundle bundle){
        Intent in = new Intent(myContext,cls);
        in.putExtra("bundle",bundle);
        startActivity(in);
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

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);

    }
    public void updateStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(SkinCompatResources.getColor(this, R.color.windowStatusBar));
        }
        // 修改状态栏字体颜色
        boolean useDarkStatusBar = getResources().getBoolean(R.bool.use_dark_status);
        int resId = SkinCompatResources.getInstance().getTargetResId(this, R.bool.use_dark_status);
        if (resId != 0) {
            useDarkStatusBar = SkinCompatResources.getInstance().getSkinResources().getBoolean(resId);
        }
        if (useDarkStatusBar) {
            SkinStatusBarUtils.setStatusBarDarkMode(this);
        } else {
            SkinStatusBarUtils.setStatusBarLightMode(this);
        }
    }
    public void updateNavigationBarColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(SkinCompatResources.getColor(myContext, id));
        }
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
    public void asyncTask(int operationType,PersonalMassageEntity personalMassageEntity,OnAsyncTaskListener listener){

        new AsyncTask<Void, Void, PersonalMassageEntity>() {
            @Override
            protected PersonalMassageEntity doInBackground(Void... voids) {

                switch (operationType){
                    /*1：插入数据*/
                    case ROOM_OPERATION_INSERT:
                        try {
                            db.roomTestDao().insert(personalMassageEntity);
                        }catch (SQLiteException e){

                        }
                        break;
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
     * 子类可通过此方法直接拿到VideoViewManager
     */
    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initData();

}
