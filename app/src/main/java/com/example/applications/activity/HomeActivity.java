package com.example.applications.activity;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.applications.R;
import com.example.applications.adapter.MyPagerAdapter;
import com.example.applications.entity.TabEntity;
import com.example.applications.fragment.HomeFragment;
import com.example.applications.fragment.MyFragment;
import com.example.applications.fragment.NewsFragment;
import com.example.applications.listener.OnBackPressedListener;
import com.example.applications.util.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements
        HomeFragment.HomeFragmentToHomeActivityListener,
        NewsFragment.NewsFragmentToHomeActivityListener {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private OnBackPressedListener mOnHomeBackPressedListener,mOnMyBackPressedListener;
    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private MyFragment myFragment;

    private String[] mTitles;
    private int[] mIconUnselectIds = {
            R.drawable.home_unselect, R.drawable.news_unselect,
            R.drawable.my_unselect};
    private int[] mIconSelectIds = {
            R.drawable.home_selected, R.drawable.news_select,
            R.drawable.my_selected};
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        commonTabLayout = findViewById(R.id.commonTabLayout);

    }

    @Override
    protected void initData() {
        // 获取字符串数组的资源ID
        int arrayResourceId = getResources().getIdentifier("home_common_tab_title", "array", getPackageName());
        // 获取字符串数组
        mTitles = getResources().getStringArray(arrayResourceId);


        // 创建 HomeFragment 实例
        homeFragment = HomeFragment.newInstance();
        newsFragment = NewsFragment.newInstance();
        myFragment = MyFragment.newInstance();
        // 将 HomeActivity 作为接口实现传递给 HomeFragment
        homeFragment.setListener(this);
        newsFragment.setListener(this);
        mFragments.add(homeFragment);
        mFragments.add(newsFragment);
        mFragments.add(myFragment);



        //每个TabEntity类对应着tab栏的未/已选图片和名字
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        //viewpager对n个fragment进行绑定mTitles：名字 mFragments：fragment
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mTitles,mFragments));

        //tab栏
        commonTabLayout.setTabData(mTabEntities);
        viewPager.setOffscreenPageLimit(mFragments.size());
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    //commonTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        int home = -1;
        int my = -1;

        my = myFragment.onBackPressed();

        if (my != 0) {
            home = homeFragment.onBackPressed();
        }

        if ((home == 2 && my == 2)){/**/
            toast(getString(R.string.toast_back_application));
        }else if(home == 1 && my ==1){
            super.onBackPressed();
        }

    }
    private void commonTabWithAnimation(final CommonTabLayout commonTabLayout,final boolean flag, final long duration) {
        // 使用ViewPropertyAnimator创建过渡动画
        if (flag){/*下*/
            commonTabLayout.animate()
                    .alpha(0f) // 设置为透明
                    .translationY(100)
                    .setDuration(duration / 2) // 设置动画时长为总时长的一半
                    .start(); // 开始动画
        }else {
            commonTabLayout.animate()
                    .alpha(1f) // 设置为不透明
                    .translationY(0)
                    .setDuration(duration / 2)
                    .start(); // 设置动画时长为总时长的一半
        }

    }
    @Override
    public void setCommonTabVisibility(int flag) {
        commonTabLayout.setVisibility(flag);
    }

    @Override
    public void setCommonTabAnimal(boolean flag) {
        Log.d("test", String.valueOf(flag));
        commonTabWithAnimation(commonTabLayout,flag,500);
    }


}