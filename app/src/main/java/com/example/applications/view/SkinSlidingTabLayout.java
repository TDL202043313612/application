package com.example.applications.view;

import android.content.Context;
import android.util.AttributeSet;

import com.example.applications.R;
import com.flyco.tablayout.SlidingTabLayout;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

public class SkinSlidingTabLayout extends SlidingTabLayout implements SkinCompatSupportable {
    public SkinSlidingTabLayout(Context context) {
        super(context);
    }

    public SkinSlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SkinSlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        boolean useDarkStatusBar = getResources().getBoolean(R.bool.use_dark_status);
        int resId = SkinCompatResources.getInstance().getTargetResId(getContext(), R.bool.use_dark_status);
        if (resId != 0) {
            useDarkStatusBar = SkinCompatResources.getInstance().getSkinResources().getBoolean(resId);
        }

        if (useDarkStatusBar){
            setBackgroundColor(SkinCompatResources.getInstance().getColor(R.color.colorPrimary));
            setTextUnselectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_dark_unselect_text));
            setTextSelectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_dark_select_text));
        }else {
            setBackgroundColor(SkinCompatResources.getInstance().getColor(R.color.colorPrimary_orange));
            setTextUnselectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_light_unselect_text));
            setTextSelectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_light_select_text));

        }
    }
    @Override
    public void applySkin() {
        setBackgroundColor(SkinCompatResources.getInstance().getColor(R.color.colorPrimary));


        boolean useDarkStatusBar = getResources().getBoolean(R.bool.use_dark_status);
        int resId = SkinCompatResources.getInstance().getTargetResId(getContext(), R.bool.use_dark_status);
        if (resId != 0) {
            useDarkStatusBar = SkinCompatResources.getInstance().getSkinResources().getBoolean(resId);
        }
        if (useDarkStatusBar){
            setTextUnselectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_dark_unselect_text));
            setTextSelectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_dark_select_text));
        }else {
            setTextUnselectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_light_unselect_text));
            setTextSelectColor(SkinCompatResources.getInstance().getColor(R.color.slidingTabLayout_light_select_text));

        }
    }

}
