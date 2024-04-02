package com.example.applications.view;

import android.content.Context;
import android.util.AttributeSet;

import com.flyco.tablayout.CommonTabLayout;

import skin.support.widget.SkinCompatSupportable;

public class SkinCommonTabLayout extends CommonTabLayout implements SkinCompatSupportable {


    public SkinCommonTabLayout(Context context) {
        super(context);
    }

    public SkinCommonTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinCommonTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void applySkin() {

    }
}
