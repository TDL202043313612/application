package com.example.applications.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applications.R;
import com.example.applications.activity.WebActivity;
import com.example.applications.adapter.NewsAdapter;
import com.example.applications.api.Api;
import com.example.applications.api.ApiConfig;
import com.example.applications.callback.TtitCallback;
import com.example.applications.entity.NewsEntity;
import com.example.applications.entity.NewsResponse;
import com.example.applications.util.BaseFragment;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.weavey.loading.lib.LoadingLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NewsFragment extends BaseFragment implements NewsAdapter.OnItemClickListener {
    private int pageNum=1;

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private List<NewsEntity> datas=new ArrayList<>();
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private LoadingLayout loadingLayout;
    private NewsFragmentToHomeActivityListener newsFragmentToHomeActivityListener;
    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();

        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        refreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        loadingLayout = findViewById(R.id.loading_layout);
    }

    @Override
    protected void initData() {
        loadingLayout.setLoadingPage(R.layout.define_loading_page);
        loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                pageNum=1;
                loadingLayout.setStatus(LoadingLayout.Loading);
                getNewsList(true);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum=1;
//                refreshLayout.finishRefresh(2000/*,false*/);
                getNewsList(true);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageNum++;
//                refreshLayout.finishLoadMore(2000/*,false*/);
                getNewsList(false);

            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsAdapter(getActivity());
        newsAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(newsAdapter);
        loadingLayout.setStatus(LoadingLayout.Loading);
        getNewsList(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItemPosition;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 获取当前屏幕上最后一个可见项的位置
                lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                // 检查是否向下滑动
                if (dy > 0) {
                    // 向下滑动逻辑
//                    Log.d("RecyclerView", "向下滑动");
                    newsFragmentToHomeActivityListener.setCommonTabAnimal(true);
                } else if (dy < 0) {
                    // 检查是否从顶部开始滑动
                    if (lastVisibleItemPosition == 0) {
                        // 向上滑动并且位于顶部逻辑
//                        Log.d("RecyclerView", "从顶部向上滑动");
                    } else {
                        // 向上滑动但不是从顶部开始逻辑
//                        Log.d("RecyclerView", "向上滑动");
                        newsFragmentToHomeActivityListener.setCommonTabAnimal(false);
                    }
                }
            }
        });
    }

    private void getNewsList(boolean isRefresh){
        //HashMap用于存储键值对
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("page", pageNum);
        params.put("limit", ApiConfig.PAGE_SIZE);
        Api.config(ApiConfig.NEWS_LIST,params).getRequest(getActivity(),new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRefresh){
                            refreshLayout.finishRefresh(true/*,false*/);
                        }else {
                            refreshLayout.finishLoadMore(true/*,false*/);
                        }
                        NewsResponse newsResponse = new Gson().fromJson(res, NewsResponse.class);
                        if (newsResponse!=null && newsResponse.getCode() == 0){
                            List<NewsEntity> list = newsResponse.getPage().getList();
                            if (list!=null && list.size()>0){
                                if (isRefresh){
                                    datas = list;
                                }else {
                                    datas.addAll(list);
                                }
                                loadingLayout.setStatus(LoadingLayout.Success);
                                newsAdapter.setMdatas(datas);
                                //提示数据更新
                                newsAdapter.notifyDataSetChanged();
                            } else {
                                if (isRefresh){
                                    loadingLayout.setStatus(LoadingLayout.Empty);
                                }else {
                                    toast(getString(R.string.toast_no_more_data));
                                }

                            }
                        }
                    }
                });

            }
            @Override
            public void onFailure(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRefresh){
                            refreshLayout.finishRefresh(true/*,false*/);
                        }else {
                            refreshLayout.finishLoadMore(true/*,false*/);
                        }
                        loadingLayout.setStatus(LoadingLayout.No_Network);
                    }
                });
            }
        });


    }

    @Override
    public void onItemClick(Serializable obj) {
        NewsEntity newsEntity = (NewsEntity) obj;
        String url = "http://192.168.31.22:8089//newsDetail?title=" + newsEntity.getAuthorName();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        navigatetoWithBundle(WebActivity.class,bundle);
    }
    public void setListener(NewsFragmentToHomeActivityListener listener) {
        this.newsFragmentToHomeActivityListener = listener;
    }
    public interface NewsFragmentToHomeActivityListener {
        void setCommonTabAnimal(boolean flag);
        // 其他操作方法
    }
}