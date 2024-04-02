package com.example.applications.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;

import com.example.applications.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

public class SkinSmartRefreshLayout extends SmartRefreshLayout implements SkinCompatSupportable {


    public SkinSmartRefreshLayout(Context context) {
        super(context);
    }

    public SkinSmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Log.d("test","SkinSmartRefreshLayout");

        init(context);
    }
    private void init(Context context){
        SharedPreferences sp = context.getSharedPreferences("sp_ttit",Context.MODE_PRIVATE);
        String skin=sp.getString("skin","");

        if (skin.equals("default")){
            setPrimaryColors(SkinCompatResources.getInstance().getColor(R.color.refresh),
                    SkinCompatResources.getInstance().getColor(R.color.white));
        }else {
            setPrimaryColors(SkinCompatResources.getInstance().getColor(R.color.refresh_orange),
                    SkinCompatResources.getInstance().getColor(R.color.white));
        }
    }
    @Override
    public void applySkin() {
        setPrimaryColors(SkinCompatResources.getInstance().getColor(R.color.refresh),
                SkinCompatResources.getInstance().getColor(R.color.white));
    }
}
