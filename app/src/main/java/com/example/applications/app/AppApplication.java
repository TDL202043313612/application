package com.example.applications.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.RuntimeABI;
import com.bumptech.glide.Glide;
import com.example.applications.R;
import com.example.applications.aop.Log;
import com.example.applications.api.ApiConfig;
import com.example.applications.arcface.Constants;
import com.example.applications.http.model.RequestHandler;
import com.example.applications.http.model.RequestServer;
import com.example.applications.manager.ActivityManager;
import com.example.applications.other.AppConfig;
import com.example.applications.other.CrashHandler;
import com.example.applications.other.DebugLoggerTree;
import com.example.applications.other.MaterialHeader;
import com.example.applications.other.SmartBallPulseFooter;
import com.example.applications.other.TitleBarStyle;
import com.example.applications.other.ToastLogInterceptor;
import com.example.applications.other.ToastStyle;
import com.example.applications.room.PersonalMassageDatabase;
import com.hjq.bar.TitleBar;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.http.EasyConfig;
import com.hjq.language.MultiLanguages;
import com.hjq.language.OnLanguageListener;
import com.hjq.toast.ToastUtils;
import com.hjq.umeng.UmengClient;
import com.qweather.sdk.view.HeConfig;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import skin.support.BuildConfig;
import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;
import skin.support.flycotablayout.app.SkinFlycoTabLayoutInflater;
import skin.support.utils.Slog;
import timber.log.Timber;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 应用入口
 */
public final class AppApplication extends Application {
//    static {
//        // 设置默认的语种（越早设置越好）
//        MultiLanguages.setDefaultLanguage(DEFAULT_LANGUAGE);
//    }
    /*用于查看哪些activity已启动*/
    private List<Activity> activityList = new ArrayList<>();
    public static PersonalMassageDatabase db;
    private Context context = this;
    boolean libraryExists = true;
    // Demo 所需的动态库文件
    private static final String[] LIBRARIES = new String[]{
            // 人脸相关
            "libarcsoft_face_engine.so",
            "libarcsoft_face.so",
            // 图像库相关
            "libarcsoft_image_util.so",
    };
    @Log("启动耗时")
    @Override
    public void onCreate() {
        super.onCreate();

        /*和风天气*/
        HeConfig.init(ApiConfig.PUBLIC_ID,ApiConfig.KEY);
        //切换至免费订阅
        HeConfig.switchToDevService();

        // 初始化语种切换框架
        MultiLanguages.init(this);


        /*初始化room*/
        // 4. 执行数据库操作
        db = PersonalMassageDatabase.getInstance(context);

        // 设置语种变化监听器
        MultiLanguages.setOnLanguageListener(new OnLanguageListener() {

            @Override
            public void onAppLocaleChange(Locale oldLocale, Locale newLocale) {
                android.util.Log.i("MultiLanguages", "监听到应用切换了语种，旧语种：" + oldLocale + "，新语种：" + newLocale);
                List<Activity> list = getAllActivity();
//                android.util.Log.i("MultiLanguages", String.valueOf(list.size()));

                for (Activity activity : list) {
//                    android.util.Log.i("MultiLanguages", activity.getClass().getSimpleName());
//                    activity.recreate();
                    activity.startActivity(new Intent(activity, activity.getClass()));
                    activity.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
                    activity.finish();
                }

            }

            @Override
            public void onSystemLocaleChange(Locale oldLocale, Locale newLocale) {
                android.util.Log.i("MultiLanguages", "监听到系统切换了语种，旧语种：" + oldLocale + "，新语种：" + newLocale +
                        "，是否跟随系统：" + MultiLanguages.isSystemLanguage(AppApplication.this));
                List<Activity> list = getAllActivity();
                for (Activity activity : list) {
                    activity.startActivity(new Intent(activity, activity.getClass()));
                    activity.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
                    activity.finish();
                }
            }
        });


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                String activityName = activity.getClass().getSimpleName(); // 获取 Activity 类名
                // 或者使用以下代码获取完整的类名（包含包名）
                // String activityName = activity.getClass().getName();

                // 输出日志或进行其他操作
//                android.util.Log.i("MultiLanguages","新增："+activityName);
                activityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                String activityName = activity.getClass().getSimpleName(); // 获取 Activity 类名
                // 或者使用以下代码获取完整的类名（包含包名）
                // String activityName = activity.getClass().getName();

                // 输出日志或进行其他操作
//                android.util.Log.i("MultiLanguages","删除："+activityName);
                activityList.remove(activity);
            }
        });

        Slog.DEBUG = BuildConfig.DEBUG;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SkinCompatManager.withoutActivity(this)
                .addInflater(new SkinAppCompatViewInflater())   // 基础控件换肤
                .addInflater(new SkinFlycoTabLayoutInflater()) // H07000223/FlycoTabLayout
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();

        SharedPreferences sp = getSharedPreferences("sp_ttit",MODE_PRIVATE);
        String skin = sp.getString("skin","");
        if (skin.equals("default") || skin.equals("")){
            // 恢复应用默认皮肤
            SkinCompatManager.getInstance().restoreDefaultTheme();
        }else {
            SkinCompatManager.getInstance().loadSkin("orange", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN); // 后缀加载

        }
        initSdk(this);


        initArcFace();
    }

    private void initArcFace(){
        libraryExists = checkSoFile(LIBRARIES);
        if (!libraryExists) {
            android.util.Log.i("initArcFace","未找到库文件，请检查是否有将.so文件放至工程的 app\\\\src\\\\main\\\\jniLibs 目录下");
            return;
        }
        activeEngine();
    }
    /**
     * 激活引擎
     *
     */
    public void activeEngine() {
        if (!libraryExists) {
            android.util.Log.i("initArcFace","未找到库文件，请检查是否有将.so文件放至工程的 app\\\\src\\\\main\\\\jniLibs 目录下");
            return;
        }

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                RuntimeABI runtimeABI = FaceEngine.getRuntimeABI();
                android.util.Log.i("initArcFace", "subscribe: getRuntimeABI() " + runtimeABI);

                long start = System.currentTimeMillis();
                int activeCode = FaceEngine.activeOnline(AppApplication.this, Constants.APP_ID, Constants.SDK_KEY);
                android.util.Log.i("initArcFace", "subscribe cost: " + (System.currentTimeMillis() - start));
                emitter.onNext(activeCode);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer activeCode) {
                        if (activeCode == ErrorInfo.MOK) {
                            android.util.Log.i("initArcFace","激活引擎成功");
                        } else if (activeCode == ErrorInfo.MERR_ASF_ALREADY_ACTIVATED) {
                            android.util.Log.i("initArcFace","引擎已激活，无需再次激活");

                        } else {
                            android.util.Log.i("initArcFace","引擎激活失败，错误码为 "+activeCode);

                        }

                        ActiveFileInfo activeFileInfo = new ActiveFileInfo();
                        int res = FaceEngine.getActiveFileInfo(AppApplication.this, activeFileInfo);
                        if (res == ErrorInfo.MOK) {
                            android.util.Log.i("initArcFace", activeFileInfo.toString());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        android.util.Log.i("initArcFace", e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    /**
     * 检查能否找到动态链接库，如果找不到，请修改工程配置
     *
     * @param libraries 需要的动态链接库
     * @return 动态库是否存在
     */
    private boolean checkSoFile(String[] libraries) {
        File dir = new File(getApplicationInfo().nativeLibraryDir);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        List<String> libraryNameList = new ArrayList<>();
        for (File file : files) {
            libraryNameList.add(file.getName());
        }
        boolean exists = true;
        for (String library : libraries) {
            exists &= libraryNameList.contains(library);
        }
        return exists;
    }
    private List<Activity> getAllActivity(){
        return activityList;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(MultiLanguages.attach(base));
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 清理所有图片内存缓存
        Glide.get(this).onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 根据手机内存剩余情况清理图片内存缓存
        Glide.get(this).onTrimMemory(level);
    }

    /**
     * 初始化一些第三方框架
     */
    @SuppressLint("MissingPermission")
    public static void initSdk(Application application) {
        // 设置标题栏初始化器
        TitleBar.setDefaultStyle(new TitleBarStyle());

        // 设置全局的 Header 构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((cx, layout) ->
                new MaterialHeader(application).setColorSchemeColors(ContextCompat.getColor(application, R.color.common_accent_color)));
        // 设置全局的 Footer 构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((cx, layout) -> new SmartBallPulseFooter(application));
        // 设置全局初始化器
        SmartRefreshLayout.setDefaultRefreshInitializer((cx, layout) -> {
            // 刷新头部是否跟随内容偏移
            layout.setEnableHeaderTranslationContent(true)
                    // 刷新尾部是否跟随内容偏移
                    .setEnableFooterTranslationContent(true)
                    // 加载更多是否跟随内容偏移
                    .setEnableFooterFollowWhenNoMoreData(true)
                    // 内容不满一页时是否可以上拉加载更多
                    .setEnableLoadMoreWhenContentNotFull(false)
                    // 仿苹果越界效果开关
                    .setEnableOverScrollDrag(false);
        });

        // 初始化吐司
        ToastUtils.init(application, new ToastStyle());
        // 设置调试模式
        ToastUtils.setDebugMode(AppConfig.isDebug());
        // 设置 Toast 拦截器
        ToastUtils.setInterceptor(new ToastLogInterceptor());

        // 本地异常捕捉
        CrashHandler.register(application);

        // 友盟统计、登录、分享 SDK
        UmengClient.init(application, AppConfig.isLogEnable());

        // Bugly 异常捕捉
        CrashReport.initCrashReport(application, AppConfig.getBuglyId(), AppConfig.isDebug());

        // Activity 栈管理初始化
        ActivityManager.getInstance().init(application);

        // MMKV 初始化
        MMKV.initialize(application);

        // 网络请求框架初始化
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        EasyConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(AppConfig.isLogEnable())
                // 设置服务器配置
                .setServer(new RequestServer())
                // 设置请求处理策略
                .setHandler(new RequestHandler(application))
                // 设置请求重试次数
                .setRetryCount(1)
                .setInterceptor((api, params, headers) -> {
                    // 添加全局请求头
                    headers.put("token", "66666666666");
                    headers.put("deviceOaid", UmengClient.getDeviceOaid());
                    headers.put("versionName", AppConfig.getVersionName());
                    headers.put("versionCode", String.valueOf(AppConfig.getVersionCode()));
                    // 添加全局请求参数
                    // params.put("6666666", "6666666");
                })
                .into();

        // 设置 Json 解析容错监听
        GsonFactory.setJsonCallback((typeToken, fieldName, jsonToken) -> {
            // 上报到 Bugly 错误列表
            CrashReport.postCatchedException(new IllegalArgumentException(
                    "类型解析异常：" + typeToken + "#" + fieldName + "，后台返回的类型为：" + jsonToken));
        });

        // 初始化日志打印
        if (AppConfig.isLogEnable()) {
            Timber.plant(new DebugLoggerTree());
        }

        // 注册网络状态变化监听
        ConnectivityManager connectivityManager = ContextCompat.getSystemService(application, ConnectivityManager.class);
        if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onLost(@NonNull Network network) {
                    Activity topActivity = ActivityManager.getInstance().getTopActivity();
                    if (!(topActivity instanceof LifecycleOwner)) {
                        return;
                    }

                    LifecycleOwner lifecycleOwner = ((LifecycleOwner) topActivity);
                    if (lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.RESUMED) {
                        return;
                    }

                    ToastUtils.show(R.string.common_network_error);
                }
            });
        }
    }
}