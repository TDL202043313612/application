package com.example.applications.fragment;

import android.os.Bundle;

import com.example.applications.R;
import com.example.applications.util.BaseFragment;


public class CollectFragment extends BaseFragment {

    public CollectFragment() {
        // Required empty public constructor
    }


    public static CollectFragment newInstance() {
        CollectFragment fragment = new CollectFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_collect;
    }



    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}