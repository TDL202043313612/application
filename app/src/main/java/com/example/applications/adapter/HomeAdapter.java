package com.example.applications.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class HomeAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private ArrayList<Fragment> mFragments;

    public void setmTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    public void setmFragments(ArrayList<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    public HomeAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return mFragments!=null && mFragments.size()>0 ?mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
