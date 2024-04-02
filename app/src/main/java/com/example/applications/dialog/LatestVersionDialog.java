package com.example.applications.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.applications.R;
import com.example.applications.aop.SingleClick;
import com.hjq.base.BaseDialog;

public class LatestVersionDialog {

    public static final class Builder
            extends BaseDialog.Builder<UpdateDialog.Builder> {
        private TextView confirm;
        public Builder(Context context) {
            super(context);
            setContentView(R.layout.activity_latest_version_dialog);
            setAnimStyle(BaseDialog.ANIM_BOTTOM);
            setCancelable(false);

            confirm = findViewById(R.id.tv_confirm);
            setOnClickListener(confirm);
        }
        @SingleClick
        @Override
        public void onClick(View view) {
            dismiss();
        }
    }



}