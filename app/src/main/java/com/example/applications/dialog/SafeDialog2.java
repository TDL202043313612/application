package com.example.applications.dialog;

import static android.content.Context.MODE_PRIVATE;
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
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.applications.R;
import com.example.applications.aop.SingleClick;
import com.example.applications.http.api.VerifyCodeApi;
import com.example.applications.http.model.HttpData;
import com.example.applications.listener.OnAsyncTaskListener;
import com.example.applications.room.PersonalMassageEntity;
import com.hjq.base.BaseDialog;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.toast.ToastUtils;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2020/02/06
 *    desc   : 身份校验对话框
 */
public final class SafeDialog2 {

    public static final class Builder
            extends CommonDialog.Builder<Builder> {

        private final TextView mOriginalPassword;


        @Nullable
        private OnListener mListener;


        public Builder(Context context) {
            super(context);
            setTitle(R.string.safe_title);
            setCustomView(R.layout.safe_dialog_2);
            mOriginalPassword = findViewById(R.id.et_original_password);
        }


        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        @SingleClick
        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            if (viewId == R.id.tv_ui_confirm) {

                if (true) {
                    Bundle bundle = new Bundle();
                    if (getStringFromSp("user_password").equals(mOriginalPassword.getText().toString())){
                        autoDismiss();
                        if (mListener == null) {
                            return;
                        }
                        mListener.onConfirm(getDialog());
                        return;
                    }else {
                        ToastUtils.show(getString(R.string.common_code_error_original_password));
                    }

                    return;
                }

                // 验证码校验
                EasyHttp.post(getDialog())
                        .api(new VerifyCodeApi())
                        .request(new OnHttpListener<HttpData<Void>>() {

                            @Override
                            public void onSucceed(HttpData<Void> data) {
                                autoDismiss();
                                if (mListener == null) {
                                    return;
                                }
                                mListener.onConfirm(getDialog());
                            }

                            @Override
                            public void onFail(Exception e) {
                                ToastUtils.show(e.getMessage());
                            }
                        });
            } else if (viewId == R.id.tv_ui_cancel) {
                autoDismiss();
                if (mListener == null) {
                    return;
                }
                mListener.onCancel(getDialog());
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
        public String getStringFromSp(String key){
            SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit",MODE_PRIVATE);
            return sp.getString(key,"");
        }
    }

    public interface OnListener {

        /**
         * 点击确定时回调
         */
        void onConfirm(BaseDialog dialog);

        /**
         * 点击取消时回调
         */
        default void onCancel(BaseDialog dialog) {}
    }
}