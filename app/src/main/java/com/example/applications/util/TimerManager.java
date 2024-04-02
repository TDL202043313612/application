package com.example.applications.util;

import android.os.Handler;

public class TimerManager {

    private Handler handler = new Handler();
    private TimerCallback timerCallback;
    private long mIntervalMillis;
    public TimerManager(TimerCallback callback) {
        this.timerCallback = callback;
    }

    // 启动定时任务
    public void startRepeatingTask(long intervalMillis) {
        mIntervalMillis = intervalMillis;
        handler.postDelayed(runnable, mIntervalMillis);
    }

    // 停止定时任务
    public void stopRepeatingTask() {
        handler.removeCallbacks(runnable);
    }

    // 定义定时任务的接口
    public interface TimerCallback {
        void onTimerTick();
    }

    // 定义要执行的任务
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 在这里执行你的定时任务逻辑
            if (timerCallback != null) {
                timerCallback.onTimerTick();
            }

            // 执行完后再次调度任务
            handler.postDelayed(this, mIntervalMillis);
        }
    };
}

