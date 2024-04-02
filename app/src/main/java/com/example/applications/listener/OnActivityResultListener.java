package com.example.applications.listener;

import android.content.Intent;

import androidx.annotation.Nullable;

public interface OnActivityResultListener {
    /*监听*/
    void onActivityResult(int resultCode, @Nullable Intent data);
}
