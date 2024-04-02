package com.example.applications.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.example.applications.R;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

public class SkinNestedScrollView extends NestedScrollView implements SkinCompatSupportable {
    public SkinNestedScrollView(@NonNull Context context) {
        super(context);
//        Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
    }

    public SkinNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        Toast.makeText(context,"2",Toast.LENGTH_SHORT).show();
        init(context);
    }

    public SkinNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        Toast.makeText(context,"3",Toast.LENGTH_SHORT).show();

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
