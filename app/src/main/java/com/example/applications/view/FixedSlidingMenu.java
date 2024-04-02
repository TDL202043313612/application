package com.example.applications.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;

import com.example.applications.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

public class FixedSlidingMenu extends SlidingMenu implements SkinCompatSupportable {
    public FixedSlidingMenu(Context context) {
        super(context);

    }

    public FixedSlidingMenu(Activity activity, int slideStyle) {
        super(activity, slideStyle);
    }

    public FixedSlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FixedSlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


    }
    private void init(Context context){
        SharedPreferences sp = context.getSharedPreferences("sp_ttit",Context.MODE_PRIVATE);
        String skin=sp.getString("skin","");

        if (skin.equals("default") || skin.equals("")){
            setBackground(SkinCompatResources.getInstance().getDrawable(R.drawable.shape_lr_info_bg));
        }else {
            setBackground(SkinCompatResources.getInstance().getDrawable(R.drawable.shape_lr_info_bg_orange));
        }
    }
    @Override
    public void applySkin() {
        setBackground(SkinCompatResources.getInstance().getDrawable(R.drawable.shape_lr_info_bg));

    }
}
