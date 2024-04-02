package com.example.applications.activity;

import static com.example.applications.api.ApiConfig.ROOM_OPERATION_QUERY;
import static com.example.applications.api.ApiConfig.ROOM_OPERATION_UPDATE;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_HEADER_PATH;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_USER_LEAVE_MESSAGE;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_USER_NAME;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_TOKEN;
import static com.example.applications.util.StringUtils.isEmpty;
import static com.example.applications.util.StringUtils.stringSub;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.applications.R;
import com.example.applications.aop.SingleClick;
import com.example.applications.app.AppActivity;
import com.example.applications.dialog.AddressDialog;
import com.example.applications.dialog.InputDialog;
import com.example.applications.http.api.UpdateImageApi;
import com.example.applications.http.glide.GlideApp;
import com.example.applications.http.model.HttpData;
import com.example.applications.listener.OnAsyncTaskListener;
import com.example.applications.room.PersonalMassageEntity;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.model.FileContentResolver;
import com.hjq.widget.layout.SettingBar;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/04/20
 *    desc   : 个人资料
 */
public final class PersonalDataActivity extends AppActivity {

    private ViewGroup mAvatarLayout;
    private ImageView mAvatarView;
    private SettingBar mIdView;
    private SettingBar mNameView;
    private SettingBar mAddressView;
    private SettingBar mMessageView;

    /*用户地址*/
    private String userAddress;
    /* 头像地址 */
    private Uri mAvatarUrl;
    /*判断是否提示myFragment需要刷新*/
    private int whetherHeaderRefresh = -1;
    private int whetherLeaveMessageRefresh = -1;
    @Override
    protected int getLayoutId() {
        return R.layout.personal_data_activity;
    }

    @Override
    protected void initView() {
        mAvatarLayout = findViewById(R.id.fl_person_data_avatar);
        mAvatarView = findViewById(R.id.iv_person_data_avatar);
        mIdView = findViewById(R.id.sb_person_data_id);
        mNameView = findViewById(R.id.sb_person_data_name);
        mAddressView = findViewById(R.id.sb_person_data_address);
        mMessageView = findViewById(R.id.sb_person_data_message);
        setOnClickListener(mAvatarLayout, mAvatarView, mNameView, mAddressView,mMessageView);
    }

    @Override
    protected void initData() {
        /*设置状态栏的颜色为白色*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }
        initPersonalData();

    }


    private void initPersonalData(){
        initHeader();
        /*设置留言*/
        mMessageView.setRightText(getStringFromSp(SHARED_PREFERENCES_USER_LEAVE_MESSAGE));

        mIdView.setRightText(stringSub(getStringFromSp("token"),
                0,
                Math.min(getStringFromSp("token").length(),6)));
        mNameView.setRightText(getStringFromSp(SHARED_PREFERENCES_USER_NAME));

        /*设置用户地址*/
        Bundle bundle = new Bundle();
        bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
        roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
            @Override
            public void onTaskCompleted(PersonalMassageEntity result) {
                userAddress = result.user_address;
                if(!isEmpty(userAddress)){
                    mAddressView.setRightText(userAddress);
                }
            }
        });

    }
    @SingleClick
    @Override
    public void onClick(View view) {
        if (view == mAvatarLayout) {
            ImageSelectActivity.start(this, data -> {
                // 裁剪头像
                cropImageFile(new File(data.get(0)));
            });
        } else if (view == mAvatarView) {

            if (mAvatarUrl != null) {
                // 查看头像
                ImagePreviewActivity.start(getActivity(), mAvatarUrl.toString());
            } else {
                // 选择头像
                onClick(mAvatarLayout);
            }
        } else if (view == mNameView) {
            new InputDialog.Builder(this)
                    // 标题可以不用填写
                    .setTitle(getString(R.string.personal_data_name_hint))
                    .setContent(mNameView.getRightText())
                    //.setHint(getString(R.string.personal_data_name_hint))
                    //.setConfirm("确定")
                    // 设置 null 表示不显示取消按钮
                    //.setCancel("取消")
                    // 设置点击按钮后不关闭对话框
                    //.setAutoDismiss(false)
                    .setListener((dialog, content) -> {
                        if (!mNameView.getRightText().equals(content)) {
                            mNameView.setRightText(content);
                        }
                    })
                    .show();
        } else if (view == mMessageView){
            new InputDialog.Builder(this)
                    // 标题可以不用填写
                    .setTitle(R.string.personal_data_leave_message_hint)
                    .setContent(mMessageView.getRightText())
                    .setListener((dialog, content) -> {
                        if (!mMessageView.getRightText().equals(content)) {
                            mMessageView.setRightText(content);
                            whetherLeaveMessageRefresh = 1;
                            Bundle bundle = new Bundle();
                            bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
                            roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
                                @Override
                                public void onTaskCompleted(PersonalMassageEntity result) {
                                    result.user_leave_message = content;
                                    roomSqlite(ROOM_OPERATION_UPDATE,result,null);
                                }
                            });
                        }
                    })
                    .show();
        } else if (view == mAddressView) {
            new AddressDialog.Builder(this)
                    .setListener((dialog, province, city, area) -> {
                        String address = province + " " + city + " " + area;
                        if (!mAddressView.getRightText().equals(address)) {
                            mAddressView.setRightText(address);
//                            saveStringToSp("user_address",address);
                            Bundle bundle = new Bundle();
                            bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
                            roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
                                @Override
                                public void onTaskCompleted(PersonalMassageEntity result) {
                                    result.user_address = address;
                                    roomSqlite(ROOM_OPERATION_UPDATE,result,null);
                                }
                            });
                        }
                    })
                    .show();
        }
    }
    private void initHeader(){
        String path = getStringFromSp(SHARED_PREFERENCES_HEADER_PATH);
        if (!isEmpty(path)){
            mAvatarUrl = Uri.parse(path);
            GlideApp.with(getActivity())
                    .load(mAvatarUrl)
                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                    .into(mAvatarView);
        }else {
            GlideApp.with(getActivity())
                    .load(R.drawable.avatar_placeholder_ic)
                    .placeholder(R.drawable.avatar_placeholder_ic)
                    .error(R.drawable.avatar_placeholder_ic)
                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                    .into(mAvatarView);
        }
    }
    /**
     * 裁剪图片
     */
    public void cropImageFile(File sourceFile) {
        ImageCropActivity.start(this, sourceFile, 1, 1, new ImageCropActivity.OnCropListener() {

            @Override
            public void onSucceed(Uri fileUri, String fileName) {
                File outputFile;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    outputFile = new FileContentResolver(getActivity(), fileUri, fileName);
                } else {
                    try {
                        outputFile = new File(new URI(fileUri.toString()));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                        outputFile = new File(fileUri.toString());
                    }
                }
                updateCropImage(outputFile, true);
            }

            @Override
            public void onError(String details) {
                // 没有的话就不裁剪，直接上传原图片
                // 但是这种情况极其少见，可以忽略不计
                updateCropImage(sourceFile, false);
            }
        });
    }



    /**
     * 上传裁剪后的图片
     */
    private void updateCropImage(File file, boolean deleteFile) {
        whetherHeaderRefresh = 1;
        if (true) {
            if (file instanceof FileContentResolver) {
                mAvatarUrl = ((FileContentResolver) file).getContentUri();
            } else {
                mAvatarUrl = Uri.fromFile(file);
            }

            GlideApp.with(getActivity())
                    .load(mAvatarUrl)
                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                    .into(mAvatarView);
//            saveStringToSp(SHARED_PREFERENCES_HEADER_PATH,mAvatarUrl.toString());

            Bundle bundle = new Bundle();
            bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
            roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
                @Override
                public void onTaskCompleted(PersonalMassageEntity result) {
                    result.avatar_path = mAvatarUrl.toString();
                    roomSqlite(ROOM_OPERATION_UPDATE,result,null);
                }
            });
            return;
        }

        EasyHttp.post(this)
                .api(new UpdateImageApi()
                        .setImage(file))
                .request(new HttpCallback<HttpData<String>>(this) {

                    @Override
                    public void onSucceed(HttpData<String> data) {
                        mAvatarUrl = Uri.parse(data.getData());
                        GlideApp.with(getActivity())
                                .load(mAvatarUrl)
                                .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                                .into(mAvatarView);
                        if (deleteFile) {
                            file.delete();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        // 在这里编写你的逻辑，监控侧边返回按钮的操作
        // 例如，你可以执行一些特定的操作，或者弹出对话框来确认是否退出界面等等
        if (whetherHeaderRefresh == 1 || whetherLeaveMessageRefresh == 1){
            Intent resultIntent = new Intent();
            if (whetherHeaderRefresh == 1) {
                resultIntent.putExtra("headerRefresh", mAvatarUrl.toString());
            }
            if (whetherLeaveMessageRefresh == 1) {
                resultIntent.putExtra("leaveMessageRefresh", mMessageView.getRightText().toString());
            }
            setResult(RESULT_OK, resultIntent);
        } else { /*取消更新操作*/
            setResult(RESULT_CANCELED);
        }
        finish();
        // 调用父类的方法，以确保默认的返回按钮行为仍然有效
        // 在下一个界面中设置返回数据
        super.onBackPressed();
    }



}