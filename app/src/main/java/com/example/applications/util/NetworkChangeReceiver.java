package com.example.applications.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private OnNetWorkChangeListener mOnNetWorkChangeListener;
    @Override
    public void onReceive(Context context, Intent intent) {

        if (mOnNetWorkChangeListener != null){
            mOnNetWorkChangeListener.onNetWorkChange();
        }

    }


    public void setOnNetWorkChangeListener(OnNetWorkChangeListener onNetWorkChangeListener) {
        mOnNetWorkChangeListener = onNetWorkChangeListener;
    }
    public interface OnNetWorkChangeListener{
        void onNetWorkChange();
    }
}