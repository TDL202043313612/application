package com.example.applications.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.applications.R;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

@SuppressLint("AppCompatCustomView")
public class SkinImageView extends ImageView implements SkinCompatSupportable {


    public SkinImageView(Context context) {
        super(context);
//        Log.d("test","1");

    }

    public SkinImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Log.d("test","2");
        init();
    }

    public SkinImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        Log.d("test","3");

    }

    public SkinImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        Log.d("test","4");

    }
    private void init(){
        boolean useDarkStatusBar = getResources().getBoolean(R.bool.use_dark_status);
        int resId = SkinCompatResources.getInstance().getTargetResId(getContext(), R.bool.use_dark_status);
        if (resId != 0) {
            useDarkStatusBar = SkinCompatResources.getInstance().getSkinResources().getBoolean(resId);
        }

        if (useDarkStatusBar){
            setImageTintList(ColorStateList.valueOf(SkinCompatResources.getInstance().getColor(R.color.icolorPrimaryReverse)));
        }else {
            setImageTintList(ColorStateList.valueOf(SkinCompatResources.getInstance().getColor(R.color.icolorPrimaryReverse_orange)));
        }
    }
    @Override
    public void applySkin() {

    }
}
