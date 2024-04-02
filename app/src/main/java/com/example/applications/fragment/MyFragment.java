package com.example.applications.fragment;

import static android.app.Activity.RESULT_OK;
import static com.example.applications.api.ApiConfig.DOUBLE_BACK_PRESS_TIME_INTERVAL;
import static com.example.applications.api.ApiConfig.LOCATION_FASTEST_INTERVAL;
import static com.example.applications.api.ApiConfig.LOCATION_INTERVAL;
import static com.example.applications.api.ApiConfig.PICK_IMAGE_REQUEST;
import static com.example.applications.api.ApiConfig.REQUEST_IMAGE_CAPTURE;
import static com.example.applications.api.ApiConfig.ROOM_OPERATION_QUERY;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_HEADER_PATH;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_USER_LEAVE_MESSAGE;
import static com.example.applications.api.ApiConfig.SHARED_PREFERENCES_USER_NAME;
import static com.example.applications.api.ApiConfig.SQLITE_COLUMN_TOKEN;
import static com.example.applications.api.ApiConfig.WEATHER_INTERVAL;
import static com.example.applications.util.DayNightDetector.isDayTime;
import static com.example.applications.util.DayNightDetector.setDayEndHour;
import static com.example.applications.util.DayNightDetector.setDayEndMinute;
import static com.example.applications.util.DayNightDetector.setDayStartHour;
import static com.example.applications.util.DayNightDetector.setDayStartMinute;
import static com.example.applications.util.NetworkUtils.getAPNType;
import static com.example.applications.util.NetworkUtils.isNetworkConnected;
import static com.example.applications.util.StringUtils.isEmpty;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.applications.R;
import com.example.applications.activity.BrowserActivity;
import com.example.applications.activity.LoginActivity;
import com.example.applications.activity.MyCollectActivity;
import com.example.applications.activity.PersonalDataActivity;
import com.example.applications.activity.SettingActivity;
import com.example.applications.aop.Permissions;
import com.example.applications.aop.SingleClick;
import com.example.applications.entity.HF3DWeatherEntity;
import com.example.applications.entity.HF3DWeatherResponse;
import com.example.applications.entity.HFCityEntity;
import com.example.applications.entity.HFCityResponse;
import com.example.applications.entity.HFNowWeatherEntity;
import com.example.applications.entity.HFNowWeatherResponse;
import com.example.applications.http.glide.GlideApp;
import com.example.applications.listener.OnActivityResultListener;
import com.example.applications.listener.OnAsyncTaskListener;
import com.example.applications.room.PersonalMassageEntity;
import com.example.applications.util.BaseFragment;
import com.example.applications.util.NetworkChangeReceiver;
import com.example.applications.util.NetworkChangeReceiver.OnNetWorkChangeListener;
import com.example.applications.util.TimerManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.hjq.permissions.Permission;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.weather.WeatherDailyBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;

import java.util.List;

import skin.support.SkinCompatManager;


public class MyFragment extends BaseFragment implements  OnNetWorkChangeListener {

    private static final int PERMISSION_REQUEST_READ_PHONE_STATE = 1001;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private LinearLayout logout,skin,collect,personalData,setting;
    private ImageView header,menuImage,state;
    private SlidingMenu menu;
    private Dialog dialog;
    private TextView title,facility,communicationTechnology,message;
    private LottieAnimationView lottieAnimationView;
    private TimerManager timerManager;
    private Handler handler = new Handler();
    private List<HFCityEntity> cityList;
    private String latitude="",
                    longitude="";
    private View menuRoot,line;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private static int count=1;
    private static int init;
    private long lastBackPressedTime = 0;
    private Handler handler2 = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                }
                break;
            }
        }
    };
    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();

        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
//        logout = mRootView.findViewById(R.id.l_logout);
        skin = findViewById(R.id.l_skin);
        collect = findViewById(R.id.l_collect);
        header = findViewById(R.id.header);
        menuImage = findViewById(R.id.menu);
        personalData = findViewById(R.id.personal_data);
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        state = findViewById(R.id.state);
        facility = findViewById(R.id.facility);
        communicationTechnology = findViewById(R.id.communication_technology);
        line = findViewById(R.id.view);



    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initData() {

        /*初始化图像*/
        initHeader();
        /*留言初始化*/
        initLeaveMessage();
        /*定位并获取天气*/
//        openLocation();
        /*侧滑菜单初始化*/
        menuInit();

        /*当前什么设备在线*/
        currentAppState();

        // 注册网络状态监听器
        registerRealNetworkChangeReceiver();

        /*设定名字*/
        title.setText(getStringFromSp("user_name"));

        setOnClickListener(setting,personalData,collect,skin,lottieAnimationView,menuImage);



    }

    @Override
    public void onStart() {
        super.onStart();
        /**/
        /*打开定位服务*/
//        startLocationUpdates();
    }

    @Override
    public void onStop() {
        super.onStop();
        // 在销毁时停止定时任务，以防内存泄漏
//        stopRepeatingTask();
        // 在销毁活动时停止位置更新
//        stopLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在销毁时停止定时任务，以防内存泄漏
        stopRepeatingTask();
        // 在销毁活动时停止位置更新
        stopLocationUpdates();
        /*把网络改变监听器注销*/
        unregisterRealNetworkChangeReceiver();
    }
    private void initLeaveMessage(){
        Bundle bundle = new Bundle();
        bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
        roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
            @Override
            public void onTaskCompleted(PersonalMassageEntity result) {
                if (!isEmpty(result.user_leave_message)){
                    message.setText(result.user_leave_message);
                    saveStringToSp(SHARED_PREFERENCES_USER_LEAVE_MESSAGE,result.user_leave_message);
                }else {
                    message.setText(getString(R.string.my_welcome_text));
                    saveStringToSp(SHARED_PREFERENCES_USER_LEAVE_MESSAGE,getString(R.string.my_welcome_text));

                }

            }
        });
    }
    @Permissions({Permission.READ_PHONE_STATE})
    private void currentAppState(){
        String facility_text;
        String communication;
        int stateId;
        int lineVisibility;
        int CTVisibility;
        if(isNetworkConnected(getContext())){
            stateId = R.drawable.online;
            lineVisibility = View.VISIBLE;
            CTVisibility = View.VISIBLE;
        }else {
            stateId = R.drawable.disconnection;
            lineVisibility = View.GONE;
            CTVisibility = View.GONE;
        }
        if (!isTablet()){
            facility_text = "手机在线";
        }else {
            facility_text = "平板在线";
        }

        /*获取当前网络状态*/
        communication = getAPNType(getContext());

        state.setImageResource(stateId);
        line.setVisibility(lineVisibility);
        facility.setText(facility_text);

        communicationTechnology.setVisibility(CTVisibility);
        communicationTechnology.setText(communication);
    }
    private void registerRealNetworkChangeReceiver(){
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        // 创建 IntentFilter，并添加需要接收的广播动作
        mNetworkChangeReceiver.setOnNetWorkChangeListener(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        // 注册广播接收器
        getActivity().registerReceiver(mNetworkChangeReceiver, filter);
    }

    private void unregisterRealNetworkChangeReceiver(){
        getActivity().unregisterReceiver(mNetworkChangeReceiver);
    }
    /**
     * 判断当前设备是否是平板
     */
    private boolean isTablet() {
        return (getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }



    private void menuInit(){
        // configure the SlidingMenu
        menu = new SlidingMenu(getContext());

        menu.setMode(SlidingMenu.RIGHT);
        // 设置触摸屏幕的模式（TOUCHMODE_MARGIN：边缘触发）
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeEnabled(true);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        menu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.right_menu);

        menuRoot = menu.getMenu();
        setting = menuRoot.findViewById(R.id.setting);
    }
    private void startRepeatingTask(){
        if (timerManager==null)
            return;
        timerManager.startRepeatingTask(WEATHER_INTERVAL);
    }
    private void stopRepeatingTask(){
        if (timerManager==null)
            return;
        timerManager.stopRepeatingTask();
    }
    private void startLocationUpdates(){
        // 创建定位请求
        if (fusedLocationClient == null)
            return;
        requestLocationUpdates();

    }

    private void stopLocationUpdates() {
        // 取消注册 LocationCallback
        if (fusedLocationClient == null)
            return;
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    // 弹出选择框
private void showImagePickerDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    LayoutInflater inflater = getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.dialog_custom_layout, null);
    builder.setView(dialogView);
    AlertDialog dialog = builder.create();
    Window window = dialog.getWindow();
    window.setGravity(Gravity.BOTTOM); // 设置对话框显示在底部
    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

    dialog.show();

    TextView textCamera = dialogView.findViewById(R.id.textCamera);
    TextView textGallery = dialogView.findViewById(R.id.textGallery);
    TextView textOpenPicture = dialogView.findViewById(R.id.textOpenPicture);
    TextView textCancel = dialogView.findViewById(R.id.textCancel);

    textCamera.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            openCamera();
        }
    });

    textGallery.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();

            openGallery();
            // 在这里添加从相册选择的点击操作
        }
    });

    textOpenPicture.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            openPicture();
            // 在这里添加查看头像的点击操作
        }
    });

    textCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 在这里添加取消的点击操作
            dialog.dismiss();
        }
    });




}




    private void openPicture(){
        AlertDialog.Builder builder_ = new AlertDialog.Builder(getContext(),R.style.DialogStyle);

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.show_picture, null, false);
        ImageView header_ = contentView.findViewById(R.id.headerImageView);
        header_.setImageDrawable(header.getDrawable());

        // 将ImageView设置为AlertDialog的视图
        //builder_.setView(header_);
        dialog = builder_.create();

        dialog.show();

         //获取AlertDialog的Window
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = point.x;  // 设置宽度和高度
        lp.height = point.y;

        window.setAttributes(lp);
        window.setContentView(contentView);

        LinearLayout linearLayout = window.findViewById(R.id.linearLayout);
        if (linearLayout != null) {
            setOnClickListener(linearLayout);
        }
        Button btn = window.findViewById(R.id.ok_btn);
        if (btn != null) {
            setOnClickListener(btn);

        }
    }
    private void openGallery(){
        /**/
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    // 从 Uri 获取 Bitmap
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 启动相机拍照
    @Permissions({Permission.CAMERA,Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE})
    private void openCamera() {
        camera();
    }
    private void camera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }


    }



    private void location(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        // 创建定位请求
        locationRequest = LocationRequest.create()
                .setInterval(LOCATION_INTERVAL)
                .setFastestInterval(LOCATION_FASTEST_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // 处理获取到的位置信息
                    latitude = Double.toString(location.getLatitude());
                    //
                    longitude = Double.toString(location.getLongitude());
                    Log.d("weather",longitude+latitude);
                    stopLocationUpdates();

                    if (init == 0){
                        init=1;
                        initWeather();
                    }

                    // 进行其他操作...
                }
            }
        };
        requestLocationUpdates();
    }
    @SuppressLint("MissingPermission")
    private void requestLocationUpdates(){

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }
    @Permissions({Permission.ACCESS_FINE_LOCATION})
    private void openLocation(){
        initLocationTimer();
        location();
    }
    private void initLocationTimer(){
        timerManager = new TimerManager(new TimerManager.TimerCallback() {
            @Override
            public void onTimerTick() {
                // 示例用法

                startLocationUpdates();
                if (count == 2){
                    count++;
                    initWeather();
                } else {
                    count=1;
                    getNowWeather();
                }
            }
        });
    }
    private void initHeader(){

        Bundle bundle = new Bundle();

        bundle.putString(SQLITE_COLUMN_TOKEN,getStringFromSp(SHARED_PREFERENCES_USER_NAME));
        roomSqlite(ROOM_OPERATION_QUERY, bundle, new OnAsyncTaskListener() {
            @Override
            public void onTaskCompleted(PersonalMassageEntity result) {
                if (result==null)return;
                String path=result.avatar_path;
//                toast(result.token+" ");
                if (!isEmpty(path)){
                    GlideApp.with(getActivity())
                            .load(path)
                            .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                            .into(header);
                }else {
                    GlideApp.with(getActivity())
                            .load(R.drawable.avatar_placeholder_ic)
                            .placeholder(R.drawable.avatar_placeholder_ic)
                            .error(R.drawable.avatar_placeholder_ic)
                            .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                            .into(header);
                }
                saveStringToSp(SHARED_PREFERENCES_HEADER_PATH,path);
            }
        });

    //        String path = getStringFromSp("avatar_path");
//        if (!path.equals("")){
//            GlideApp.with(getActivity())
//                    .load(path)
//                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
//                    .into(header);
//        }else {
//            GlideApp.with(getActivity())
//                    .load(R.drawable.avatar_placeholder_ic)
//                    .placeholder(R.drawable.avatar_placeholder_ic)
//                    .error(R.drawable.avatar_placeholder_ic)
//                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
//                    .into(header);
//        }
    }
    private void initWeather(){
        QWeather.getGeoCityLookup(getContext(), longitude+","+latitude, new QWeather.OnResultGeoListener(){

            @Override
            public void onError(Throwable throwable) {
                error(throwable);
            }

            @Override
            public void onSuccess(GeoBean geoBean) {
                Log.d("weather","获取城市成功： " + new Gson().toJson(geoBean));
                String gson=new Gson().toJson(geoBean);
                HFCityResponse hfCityResponse = new Gson().fromJson(gson,HFCityResponse.class);
                if (hfCityResponse.getCode().equals("OK")){
                    cityList = hfCityResponse.getLocationBean();
//                    Log.d("weather","city： " + cityList.get(0).getName());

                    /*获取三天天气cityList.get(0).getLon()+","+cityList.get(0).getLat()*/
                    QWeather.getWeather3D(getContext(),longitude+","+latitude , new QWeather.OnResultWeatherDailyListener(){

                        @Override
                        public void onError(Throwable throwable) {
                            error(throwable);
                        }

                        @Override
                        public void onSuccess(WeatherDailyBean weatherDailyBean) {
                            Log.d("weather","3D获取天气成功： " + new Gson().toJson(weatherDailyBean));
                            String gson=new Gson().toJson(weatherDailyBean);
                            HF3DWeatherResponse hf3DWeatherResponse = new Gson().fromJson(gson,HF3DWeatherResponse.class);
                            if (hf3DWeatherResponse.getCode().equals("OK")) {
                                List<HF3DWeatherEntity> list = hf3DWeatherResponse.getDaily();
                                String[] sunrise = list.get(0).getSunrise().split(":");
                                String[] sunset = list.get(0).getSunset().split(":");
                                setDayStartHour(Integer.parseInt(sunrise[0]));
                                setDayStartMinute(Integer.parseInt(sunrise[1]));

                                setDayEndHour(Integer.parseInt(sunset[0]));
                                setDayEndMinute(Integer.parseInt(sunset[1]));
                            }
                        }
                    });

                    /*实时获取天气  cityList.get(0).getLon()+","+cityList.get(0).getLat()*/
                    QWeather.getWeatherNow(getContext(),longitude+","+latitude, new QWeather.OnResultWeatherNowListener(){

                        @Override
                        public void onError(Throwable throwable) {
//                            startRepeatingTask();
                            error(throwable);
                        }



                        @Override
                        public void onSuccess(WeatherNowBean weatherNowBean) {
                            Log.d("weather","NOW获取天气成功： " + new Gson().toJson(weatherNowBean));
                            String gson=new Gson().toJson(weatherNowBean);
                            HFNowWeatherResponse hfNowWeatherResponse = new Gson().fromJson(gson,HFNowWeatherResponse.class);
                            if (hfNowWeatherResponse.getCode().equals("OK")) {
                                HFNowWeatherEntity hfNowWeatherEntity = hfNowWeatherResponse.getNow();
                                // 启动定时任务
                                startRepeatingTask();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        weather(hfNowWeatherEntity);
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

    }
    private void getNowWeather(){
        /*实时获取天气*/
        QWeather.getWeatherNow(getContext(),longitude+","+latitude, new QWeather.OnResultWeatherNowListener(){

            @Override
            public void onError(Throwable throwable) {
                error(throwable);
            }

            @Override
            public void onSuccess(WeatherNowBean weatherNowBean) {
                Log.d("weather","NOW获取天气成功： " + new Gson().toJson(weatherNowBean));
                String gson=new Gson().toJson(weatherNowBean);
                HFNowWeatherResponse hfNowWeatherResponse = new Gson().fromJson(gson,HFNowWeatherResponse.class);
                if (hfNowWeatherResponse.getCode().equals("OK")) {
                    HFNowWeatherEntity hfNowWeatherEntity = hfNowWeatherResponse.getNow();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                                    stopLocationUpdates();
                            weather(hfNowWeatherEntity);
                        }
                    });
                }
            }
        });
    }
    private void weather(HFNowWeatherEntity now){
        // 停止当前动画
        lottieAnimationView.cancelAnimation();
        if (isDayTime()) {
            switch (now.getText()) {
                case "阴":
                    lottieAnimationView.setAnimation(R.raw.overcast);
                    break;
                case "晴":
                    lottieAnimationView.setAnimation(R.raw.sun);
                    break;
                case "多云":
                case "少云":
                case "晴间多云":
                    lottieAnimationView.setAnimation(R.raw.cloudy_day);
                    break;
                case "小雨":
                    lottieAnimationView.setAnimation(R.raw.light_rain);
                    break;
                case "中雨":
                    lottieAnimationView.setAnimation(R.raw.middle_rain);
                    break;
                case "大雨":
                    lottieAnimationView.setAnimation(R.raw.heavy_rain);
                    break;
            }
        } else {
            switch (now.getText()) {
                case "阴":
                    lottieAnimationView.setAnimation(R.raw.overcast);
                    break;
                case "晴":
                    lottieAnimationView.setAnimation(R.raw.sunny_night);
                    break;
                case "多云":
                case "少云":
                case "晴间多云":
                    lottieAnimationView.setAnimation(R.raw.night_cloudy);
                    break;
                case "小雨":
                    lottieAnimationView.setAnimation(R.raw.light_rain);
                    break;
                case "中雨":
                    lottieAnimationView.setAnimation(R.raw.middle_rain);
                    break;
                case "大雨":
                    lottieAnimationView.setAnimation(R.raw.heavy_rain);
                    break;
            }
        }

        // 设置循环
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);

        // 开始动画
        lottieAnimationView.playAnimation();

    }


    private void layoutLogout(){
        saveStringToSp("token","");
        int flag = Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK;
        navigatetoWithFlag(LoginActivity.class,flag);
    }
    private void layoutSkin(){
        String skin = getStringFromSp("skin");
        if (skin.equals("orange")){
            // 恢复应用默认皮肤
            SkinCompatManager.getInstance().restoreDefaultTheme();
            saveStringToSp("skin","default");
        }else {
            // 指定皮肤插件
            SkinCompatManager.getInstance().loadSkin("orange", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN); // 后缀加载
            saveStringToSp("skin","orange");
        }

        updateStatusBarColor();
    }

    /**/
    @Override
    public void onNetWorkChange() {
        currentAppState();
    }

    public int onBackPressed() {
        // 在这里处理返回键事件
        if (menu != null && menu.isMenuShowing()) {
            // 如果搜索视图打开，则关闭搜索视图
            menu.toggle(true);
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

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.l_skin){
            layoutSkin();
        }else if (id == R.id.l_collect){
            navigateto(MyCollectActivity.class);
        }else if(id == R.id.ok_btn || id == R.id.linearLayout){
            dialog.dismiss();
        }else if (id == R.id.personal_data){
            navigatetoForResult(PersonalDataActivity.class, new OnActivityResultListener() {
                @Override
                public void onActivityResult(int resultCode, @Nullable Intent data) {
                    if(resultCode == RESULT_OK){
                        String newHeader = data.getStringExtra("headerRefresh");
                        String newLeaveMessage = data.getStringExtra("leaveMessageRefresh");
                        if (!isEmpty(newHeader)){
                            GlideApp.with(getActivity())
                                    .load(newHeader)
                                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                                    .into(header);
                            saveStringToSp(SHARED_PREFERENCES_HEADER_PATH,newHeader);
                        }
                        if (!isEmpty(newLeaveMessage)){
                            message.setText(newLeaveMessage);
                            saveStringToSp(SHARED_PREFERENCES_USER_LEAVE_MESSAGE,newLeaveMessage);
                        }
                    }
                }
            });
        }else if (id == R.id.lottieAnimationView){
            if (cityList!=null && cityList.size()>0){
                BrowserActivity.start(getContext(), cityList.get(0).getFxLink());
            }else {
                toast(getString(R.string.toast_error_network_location));
            }
        }else if (id == R.id.menu){
            menu.toggle(true);
        }else if (id == R.id.setting){
            menu.toggle(false);

            navigateto(SettingActivity.class);
        }
    }

}