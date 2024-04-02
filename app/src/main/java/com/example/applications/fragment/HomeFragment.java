package com.example.applications.fragment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.applications.api.ApiConfig.DOUBLE_BACK_PRESS_TIME_INTERVAL;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.applications.R;
import com.example.applications.activity.ErnieBotActivity;
import com.example.applications.adapter.HomeAdapter;
import com.example.applications.api.Api;
import com.example.applications.api.ApiConfig;
import com.example.applications.callback.TtitCallback;
import com.example.applications.entity.CategoryEntity;
import com.example.applications.entity.VideoCategoryResponse;
import com.example.applications.entity.VideoEntity;
import com.example.applications.entity.VideoListResponse;
import com.example.applications.util.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.weavey.loading.lib.LoadingLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends BaseFragment implements VideoFragment.VideoFragmentToHomeFragmentListener {
    private ArrayList<Fragment> mFragments;
    private LoadingLayout loadingLayout;
    private String[] mTitles;
    private HomeAdapter homeAdapter;
    private MaterialSearchView searchView;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private Toolbar toolbar;
    private HomeFragmentToHomeActivityListener homeFragmentToHomeActivityListener;
    private List<String> stringList = new ArrayList<>();
    private long lastBackPressedTime = 0;
    private Context context = getContext();
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0: {
                    homeAdapter.setmFragments(mFragments);
                    homeAdapter.notifyDataSetChanged();
                    slidingTabLayout.notifyDataSetChanged();
                }break;
                case 1:{
                    loadingLayout.setStatus(LoadingLayout.Success);
                }break;
                case 2:{
                    loadingLayout.setStatus(LoadingLayout.Error);
                }break;
                case 3:{
                    loadingLayout.setStatus(LoadingLayout.No_Network);
                }break;
                case 4:{
                    searchView.setSuggestions(stringList.toArray(new String[0]));
                }break;
            }
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        slidingTabLayout = findViewById(R.id.slidingTabLayout);
        loadingLayout = findViewById(R.id.loading_layout);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        toolbar = findViewById(R.id.toolbar);

    }



    @Override
    protected void initData() {

        /*搜索栏初始化*/
        initSearchView();

        /*初始化首页内容区域界面*/
        initContentLayout();
        /*获取视屏分类id（传给VideoFragment）*/
        getVideoCategoryList();

        /*获取全部视频（用于searchView的提示信息）*/
        getVideoAllList();


    }

    private void initContentLayout(){
        // 获取字符串数组的资源ID
        int arrayResourceId = getResources().getIdentifier("home_sliding_tab_title", "array", getPackageName());
        // 获取字符串数组
        mTitles = getResources().getStringArray(arrayResourceId);
        /*Adapter*/
        homeAdapter = new HomeAdapter(getChildFragmentManager());
        /**/
        viewPager.setAdapter(homeAdapter);

        /*一次性全部加载*/
        viewPager.setOffscreenPageLimit(mTitles.length);

        /*slidingTabLayout的标题*/
        homeAdapter.setmTitles(mTitles);

        /*初始化界面（）*/
        mFragments = new ArrayList<>();
        for (int i=0;i<mTitles.length;i++){
            /*获取分类名*/
            mFragments.add(ErrorFragment.newInstance());
        }

        /*设置slidingTabLayout的每个标题的界面*/
        homeAdapter.setmFragments(mFragments);



        /**/
        slidingTabLayout.setViewPager(viewPager);


        loadingLayout.setLoadingPage(R.layout.define_loading_page);
        loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadingLayout.setStatus(LoadingLayout.Loading);
                getVideoCategoryList();
                getVideoAllList();
            }
        });

        /*这个动画表示内容还在加载中*/
        loadingLayout.setStatus(LoadingLayout.Loading);

    }

    private void initSearchView(){
        setHasOptionsMenu(true); // 告诉 Fragment 具有菜单项
        // 获取关联的 Activity，并将 Toolbar 设置为 ActionBar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false); // 隐藏标题

        /*关闭语音输入*/
        searchView.setVoiceSearch(false);
        /*设置输入提示*/
        String[] videoTitle={" "};
        searchView.setSuggestions(videoTitle);

        searchView.setOnQueryTextListener(new MaterialSearch());
        searchView.setOnSearchViewListener(new MaterialSearch());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item); // 这里的 searchView 是你之前在 Fragment 中初始化的 MaterialSearchView 对象
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 处理item的选择
        switch (item.getItemId()) {
            case R.id.action_ai:
                navigateto(ErnieBotActivity.class);
                return true;
            default:
                // 如果ID没有匹配任何已处理的项，则将事件传递到超类处理
                return super.onOptionsItemSelected(item);
        }
    }


    private void getVideoCategoryList(){
        //HashMap用于存储键值对
        HashMap<String,Object> params = new HashMap<String,Object>();
        Api.config(ApiConfig.VIDEO_CATEGORY_LIST,params).getRequest(getActivity(),new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                VideoCategoryResponse videoCategoryResponse = new Gson().fromJson(res, VideoCategoryResponse.class);
                if (videoCategoryResponse!=null && videoCategoryResponse.getCode() == 0){
                    List<CategoryEntity> list = videoCategoryResponse.getPage().getList();
//                    mTitles = new String[list.size()];
                    mFragments = new ArrayList<>();
                    for (int i=0;i<list.size();i++){
                        /*获取分类名*/
//                        mTitles[i] = list.get(i).getCategoryName();
                        VideoFragment videoFragment = VideoFragment.getInstance(list.get(i).getCategoryId());

                        videoFragment.setListener(HomeFragment.this);
                        mFragments.add(videoFragment);
                    }
                    handler.sendEmptyMessage(1);/*Success*/
                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessage(2);/*Error*/

                }
            }
            @Override
            public void onFailure(Exception e) {
                mFragments = new ArrayList<>();
                for (int i=0;i<mTitles.length;i++){
                    /*获取分类名*/
                    mFragments.add(ErrorFragment.newInstance());
                }

                handler.sendEmptyMessage(0);
                handler.sendEmptyMessage(3);/*No_Network*/
            }
        });
    }

    private void getVideoAllList(){
        //HashMap用于存储键值对
        HashMap<String,Object> params = new HashMap<String,Object>();
        Api.config(ApiConfig.VIDEO_LIST,params).getRequest(getActivity(), new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                VideoListResponse videoListResponse = new Gson().fromJson(res, VideoListResponse.class);
                if (videoListResponse!=null && videoListResponse.getCode() == 0){
                    List<VideoEntity> list = videoListResponse.getPage().getList();

                    for (VideoEntity videoEntity:list){
                        stringList.add(videoEntity.getVtitle());
                        stringList.add(videoEntity.getAuthor());
                    }
                    handler.sendEmptyMessage(4);
                } else {
//                    toast("getVideoAllList fail");

                }
            }

            @Override
            public void onFailure(Exception e) {
//                toast("getVideoAllList fail");

            }
        });
    }




    private class MaterialSearch implements
            com.miguelcatalan.materialsearchview.MaterialSearchView.OnQueryTextListener,
            MaterialSearchView.SearchViewListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Snackbar.make(mRootView.findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                    .show();

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
//            toast("onQueryTextChange");

            return false;
        }

        @Override
        public void onSearchViewShown() {
            // 调用接口方法来控制标签
            if (homeFragmentToHomeActivityListener != null) {
                homeFragmentToHomeActivityListener.setCommonTabVisibility(GONE);
            }
            searchView.setVisibility(View.VISIBLE);

//            toast("onSearchViewShown");

        }

        @Override
        public void onSearchViewClosed() {
            // 调用接口方法来控制标签
            if (homeFragmentToHomeActivityListener != null) {
                homeFragmentToHomeActivityListener.setCommonTabVisibility(VISIBLE);
            }
            searchView.setVisibility(GONE);
//            toast("onSearchViewClosed");

        }
    }

    public int onBackPressed() {
        // 在这里处理返回键事件
        if (searchView != null && searchView.isSearchOpen()) {
            // 如果搜索视图打开，则关闭搜索视图
            searchView.closeSearch();
            return 0;
        } else {
            // 如果搜索视图是关闭的，则判断是否双击了返回键
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastBackPressedTime< DOUBLE_BACK_PRESS_TIME_INTERVAL) {
                // 如果两次按返回键的时间间隔小于预定义的时间间隔，则退出应用程序
//                requireActivity().finish();
                return 1;
            } else {
                // 否则更新上一次按返回键的时间，并显示提示信息
                lastBackPressedTime = currentTime;
                return 2;
            }
        }
        // 处理回调逻辑
    }
    @Override
    public void setCommonTabAnimal(boolean flag) {
//        Log.d("test", String.valueOf(flag));
        homeFragmentToHomeActivityListener.setCommonTabAnimal(flag);
    }
    public void setListener(HomeFragmentToHomeActivityListener listener) {
        this.homeFragmentToHomeActivityListener = listener;
    }
    public interface HomeFragmentToHomeActivityListener {
        void setCommonTabVisibility(int flag);
        void setCommonTabAnimal(boolean flag);
        // 其他操作方法
    }




}