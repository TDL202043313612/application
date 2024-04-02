package com.example.applications.util;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import static com.example.applications.api.ApiConfig.ROOM_OPERATION_ALL_QUERY;
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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.dueeeke.videoplayer.player.VideoViewManager;
import com.example.applications.R;
import com.example.applications.action.ToastAction;
import com.example.applications.listener.OnActivityResultListener;
import com.example.applications.listener.OnAsyncTaskListener;
import com.example.applications.room.PersonalMassageEntity;
import com.hjq.base.action.ClickAction;

import java.util.Random;

import skin.support.content.res.SkinCompatResources;



public abstract class BaseFragment extends Fragment implements ToastAction, ClickAction {
    protected View mRootView;
    private OnActivityResultListener mOnActivityResultListener;


    /** 错误结果码 */
    public static final int RESULT_ERROR = -2;

    /** Activity 回调集合 */
    private SparseArray<OnActivityResultListener> mActivityResultListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(initLayout(),container,false);
            initView();
        }

//        /*把View绑定进来*/
//        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*解绑*/
//        unbinder.unbind();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    public void navigateto(Class cls){
        Intent in = new Intent(getActivity(),cls);
        startActivity(in);
    }
    public void navigatetoWithFlag(Class cls,int flag){
        Intent in = new Intent(getActivity(),cls);
        in.setFlags(flag);
        startActivity(in);
    }
    public void navigatetoWithBundle(Class cls,Bundle url){
        Intent in = new Intent(getActivity(),cls);
        in.putExtra("url",url);
        startActivity(in);
    }
    public void navigatetoForResult(Class cls,OnActivityResultListener callback){
//        Intent in = new Intent(getActivity(),cls);
//        startActivityForResult(in, code);
        startActivityForResult(cls,callback);

    }

    /**
     * startActivityForResult 方法优化
     */

    public void startActivityForResult(Class<? extends Activity> clazz, OnActivityResultListener callback) {
        startActivityForResult(new Intent(getActivity(), clazz), null, callback);
    }

    public void startActivityForResult(Intent intent, OnActivityResultListener callback) {
        startActivityForResult(intent, null, callback);
    }

    public void startActivityForResult(Intent intent, @Nullable Bundle options, OnActivityResultListener callback) {
        if (mActivityResultListener == null) {
            mActivityResultListener = new SparseArray<>(1);
        }
        // 请求码必须在 2 的 16 次方以内
        int requestCode = new Random().nextInt((int) Math.pow(2, 16));
        mActivityResultListener.put(requestCode, callback);
        startActivityForResult(intent, requestCode, options);
    }

    public void navigatetoFinish(Class cls){
        getActivity().finish();
        Intent in = new Intent(getActivity(),cls);
        startActivity(in);
    }

    public void saveStringToSp(String key,String data){
        SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit",MODE_PRIVATE);
        sp.edit().putString(key,data)
                .commit();
    }
    public String getStringFromSp(String key){
        SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit",MODE_PRIVATE);
        return sp.getString(key,"");
    }
    public void error(Throwable throwable){
        String errorMessage = throwable.getMessage();
        Throwable cause = throwable.getCause();
        Log.d("weather","Error Message: " + errorMessage);
        if (cause != null) {
            Log.d("weather","Cause: " + cause.getMessage());
            // 如果存在原因，也可以递归地输出原因的堆栈信息
            cause.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)



    public void updateStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(SkinCompatResources.getColor(getContext(), R.color.windowStatusBar));
        }
        // 修改状态栏字体颜色
        boolean useDarkStatusBar = getResources().getBoolean(R.bool.use_dark_status);
        int resId = SkinCompatResources.getInstance().getTargetResId(getContext(), R.bool.use_dark_status);
        if (resId != 0) {
            useDarkStatusBar = SkinCompatResources.getInstance().getSkinResources().getBoolean(resId);
        }
        if (useDarkStatusBar) {
            SkinStatusBarUtils.setStatusBarDarkMode(getActivity());
        } else {
            SkinStatusBarUtils.setStatusBarLightMode(getActivity());
        }
    }

    // 在上一个界面的 onActivityResult() 方法中处理返回的数据
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        OnActivityResultListener callback;
        if (mActivityResultListener != null && (callback = mActivityResultListener.get(requestCode)) != null) {
            if (resultCode == RESULT_OK)
            callback.onActivityResult(resultCode, data);
            mActivityResultListener.remove(requestCode);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setOnActivityResultListener(OnActivityResultListener onActivityResultListener) {
        mOnActivityResultListener = onActivityResultListener;
    }

    @Override
    public <V extends View> V findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    /**        setOnActivityResultListener(this);

     * Java中的延时方法
     *
     * @param millis 延时时间，单位毫秒
     */
    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                    case ROOM_OPERATION_INSERT: db.roomTestDao().insert(personalMassageEntity);break;
                    /*2：删除数据*/
                    case ROOM_OPERATION_DELETE: db.roomTestDao().delete(personalMassageEntity);break;
                    /*3：更新数据*/
                    case ROOM_OPERATION_UPDATE: db.roomTestDao().update(personalMassageEntity);break;
                    /*4：查询数据*/
                    case ROOM_OPERATION_QUERY: return db.roomTestDao().getByToken(personalMassageEntity.token);
                    /*4：查询全部数据*/
                    case ROOM_OPERATION_ALL_QUERY: toast(db.roomTestDao().getAll().size());

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
