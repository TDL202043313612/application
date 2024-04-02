package com.example.applications.activity;

import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_FACE_LOGIN;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_USER_NAME;

import android.view.View;

import com.example.applications.R;
import com.example.applications.app.AppActivity;
import com.example.applications.arcface.InitRegisterActivity;
import com.example.applications.arcface.faceserver.FaceServer;
import com.example.applications.dialog.MessageDialog;
import com.hjq.base.BaseDialog;
import com.hjq.widget.layout.SettingBar;
import com.hjq.widget.view.SwitchButton;

public class SettingFaceLoginActivity extends AppActivity
        implements SwitchButton.OnCheckedChangeListener {

    private SettingBar mFaceDeleteView,mFaceRecordingView;
    private SwitchButton mFaceSwitchView;
    private String userName = null;
    private SettingBar settingBar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_face_login;
    }

    @Override
    protected void initView() {
        mFaceDeleteView = findViewById(R.id.sb_face_delete);
        mFaceRecordingView = findViewById(R.id.sb_face_recording);
        mFaceSwitchView = findViewById(R.id.sb_face_switch);
    }

    @Override
    protected void initData() {
        // 设置切换按钮的监听
        mFaceSwitchView.setOnCheckedChangeListener(this);
        userName = getStringFromSp(SHARED_PREFERENCES_USER_NAME);
        setOnClickListener(mFaceDeleteView,mFaceRecordingView,mFaceSwitchView);
        if (getStringFromSp(userName+SHARED_PREFERENCES_FACE_LOGIN,"false").equals("true")){
            mFaceSwitchView.setChecked(true);
        }else {
            mFaceSwitchView.setChecked(false);

        }
    }

    @Override
    public void onClick(View view) {
        if (view == mFaceRecordingView){/*人脸录入*/
            startActivity(InitRegisterActivity.class);
        }else if (view == mFaceDeleteView){/*人脸删除*/
            int faceNum = FaceServer.getInstance().getFaceNumberByName(this,userName);
            if (faceNum == 0) {
                toast(getString(R.string.batch_process_no_face_need_to_delete));
            }else {
                // 消息对话框
                new MessageDialog.Builder(getActivity())
                        // 标题可以不用填写
                        .setTitle(R.string.batch_process_notification)
                        // 内容必须要填写
                        .setMessage(getString(R.string.batch_process_confirm_delete, faceNum))
                        // 确定按钮文本
                        .setConfirm(getString(R.string.common_confirm))
                        // 设置 null 表示不显示取消按钮
                        .setCancel(getString(R.string.common_cancel))
                        .setListener(new MessageDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog) {
//                                int deleteCount = FaceServer.getInstance().clearFacesByName(SettingFaceLoginActivity.this, userName);
                                int deleteCount = FaceServer.getInstance().clearAllFaces(SettingFaceLoginActivity.this);

                                if (deleteCount >= 1) {
                                    toast("删除成功，一共删除" + deleteCount + "个");
                                } else {
                                    toast("删除失败，没有人脸");
                                }
                            }
                            @Override
                            public void onCancel(BaseDialog dialog) {
                            }
                        })
                        .show();
            }
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton button, boolean checked) {
        if (checked){/*人脸识别开启*/
            saveStringToSp(userName+SHARED_PREFERENCES_FACE_LOGIN,"true");
        }else {/*人脸识别关闭*/
            saveStringToSp(userName+SHARED_PREFERENCES_FACE_LOGIN,"false");
        }
    }
}