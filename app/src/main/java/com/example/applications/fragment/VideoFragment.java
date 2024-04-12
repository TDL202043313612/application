package com.example.applications.fragment;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.example.applications.R;
import com.example.applications.adapter.VideoAdapter;
import com.example.applications.api.Api;
import com.example.applications.api.ApiConfig;
import com.example.applications.callback.TtitCallback;
import com.example.applications.entity.VideoEntity;
import com.example.applications.entity.VideoListResponse;
import com.example.applications.listener.OnItemChildClickListener;
import com.example.applications.listener.OnItemClickListener;
import com.example.applications.util.BaseFragment;
import com.example.applications.util.Tag;
import com.example.applications.util.Utils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class VideoFragment extends BaseFragment implements OnItemChildClickListener, OnItemClickListener{
    private int mId;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private int pageNum = 1;
    private List<VideoEntity> datas = new ArrayList<>();
    private VideoAdapter videoAdapter;
    private LinearLayoutManager linearLayoutManager;

    protected VideoView mVideoView;
    protected StandardVideoController mController;
    protected ErrorView mErrorView;
    protected CompleteView mCompleteView;
    protected TitleView mTitleView;
    private VideoFragmentToHomeFragmentListener videoFragmentToHomeFragmentListener;

    /**
     * 当前播放的位置
     */
    protected int mCurPos = -1;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
    protected int mLastPos = mCurPos;


    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        refreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        initVideoView();

    }

    @Override
    protected void initData() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                getVideoList(true);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageNum++;
                getVideoList(false);

            }
        });


        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        videoAdapter = new VideoAdapter(getActivity());
        /*函数回调*/
        videoAdapter.setOnItemChildClickListener(this);
        videoAdapter.setOnItemClickListener(this);

        recyclerView.setAdapter(videoAdapter);
        getVideoList(true);

        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                FrameLayout playerContainer = view.findViewById(R.id.player_container);
                View v = playerContainer.getChildAt(0);
                if (v != null && v == mVideoView && !mVideoView.isFullScreen()) {
                    releaseVideoView();
                }
            }
        });

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
                    videoFragmentToHomeFragmentListener.setCommonTabAnimal(true);
                } else if (dy < 0) {
                    // 检查是否从顶部开始滑动
                    if (lastVisibleItemPosition == 0) {
                        // 向上滑动并且位于顶部逻辑
                    } else {
                        // 向上滑动但不是从顶部开始逻辑
                        videoFragmentToHomeFragmentListener.setCommonTabAnimal(false);
                    }
                }
            }
        });
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    videoAdapter.setMdatas(datas);
                    //提示数据更新
                    videoAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
    };

    private void getVideoList(boolean isRefresh) {
        //HashMap用于存储键值对
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("page", pageNum);
        params.put("limit", ApiConfig.PAGE_SIZE);
        params.put("categoryId", mId);
        Api.config(ApiConfig.VIDEO_LIST_BY_CATEGORY, params).getRequest(getActivity(), new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(true/*,false*/);
                } else {
                    refreshLayout.finishLoadMore(true/*,false*/);
                }
                VideoListResponse videoListResponse = new Gson().fromJson(res, VideoListResponse.class);
                if (videoListResponse != null && videoListResponse.getCode() == 0) {
                    List<VideoEntity> list = videoListResponse.getPage().getList();
                    if (list != null && list.size() > 0) {
                        if (isRefresh) {
                            datas = list;
                        } else {
                            datas.addAll(list);
                        }
                        handler.sendEmptyMessage(0);
//                                    videoAdapter.setMdatas(datas);
//                                    //提示数据更新
//                                    videoAdapter.notifyDataSetChanged();
                    } else {
                        if (isRefresh) {
                            toast(getString(R.string.toast_no_data));
                        } else {
                            toast(getString(R.string.toast_no_more_data));
                        }

                    }
                }

            }

            @Override
            public void onFailure(Exception e) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(true/*,false*/);
                } else {
                    refreshLayout.finishLoadMore(true/*,false*/);
                }
                toast(getString(R.string.toast_error_network));
            }
        });


    }
//    @Override
//    protected boolean isLazyLoad() {
//        return true;
//    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    /**
     * 由于onPause必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onPause的逻辑
     */
    protected void pause() {
        releaseVideoView();
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    /**
     * 由于onResume必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onResume的逻辑
     */
    protected void resume() {
        if (mLastPos == -1)
            return;
        //恢复上次播放的位置
        startPlay(mLastPos);
    }

    /**
     * PrepareView被点击
     */
    @Override
    public void onItemChildClick(int position) {
        startPlay(position);
    }

    @Override
    public void onItemClick(int position) {
        startPlay(position);
    }

    /**
     * 开始播放
     *
     * @param position 列表位置
     */
    protected void startPlay(int position) {
        if (mCurPos == position) return;
        if (mCurPos != -1) {
            releaseVideoView();
        }
        VideoEntity videoBean = datas.get(position);
        //边播边存
//        String proxyUrl = ProxyVideoCacheManager.getProxy(getActivity()).getProxyUrl(videoBean.getUrl());
//        mVideoView.setUrl(proxyUrl);

        mVideoView.setUrl(videoBean.getPlayurl());
        mTitleView.setTitle(videoBean.getVtitle());
        View itemView = linearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        VideoAdapter.ViewHolder viewHolder = (VideoAdapter.ViewHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isDissociate此处只能为true, 请点进去看isDissociate的解释
        mController.addControlComponent(viewHolder.mPrepareView, true);
        Utils.removeViewFormParent(mVideoView);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        //播放之前将VideoView添加到VideoViewManager以便在别的页面也能操作它
        getVideoViewManager().add(mVideoView, Tag.LIST);
        mVideoView.start();
        mCurPos = position;

    }

    public static VideoFragment getInstance(int id) {
        VideoFragment sf = new VideoFragment();
        sf.mId = id;
        return sf;
    }

    /**
     * 视频释放
     */
    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if (getActivity().getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }

    /**
     * 初始化
     */
    protected void initVideoView() {
        mVideoView = new VideoView(getActivity());
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                //监听VideoViewManager释放，重置状态
                if (playState == VideoView.STATE_IDLE) {
                    Utils.removeViewFormParent(mVideoView);
                    mLastPos = mCurPos;
                    mCurPos = -1;
                }
            }
        });
        mController = new StandardVideoController(getActivity());
        mErrorView = new ErrorView(getActivity());
        mController.addControlComponent(mErrorView);
        mCompleteView = new CompleteView(getActivity());
        mController.addControlComponent(mCompleteView);
        mTitleView = new TitleView(getActivity());
        mController.addControlComponent(mTitleView);
        mController.addControlComponent(new VodControlView(getActivity()));
        mController.addControlComponent(new GestureView(getActivity()));
        mController.setEnableOrientation(true);
        mVideoView.setVideoController(mController);
    }

    public void setListener(VideoFragmentToHomeFragmentListener listener) {
        this.videoFragmentToHomeFragmentListener = listener;
    }
    public interface VideoFragmentToHomeFragmentListener {
        void setCommonTabAnimal(boolean flag);
        // 其他操作方法
    }

}