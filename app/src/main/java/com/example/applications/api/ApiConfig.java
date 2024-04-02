package com.example.applications.api;

import com.hjq.language.LocaleContract;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class ApiConfig {
    public static final int PAGE_SIZE = 5;

    public static final String BASE_URL = "http://192.168.211.230:8080/renren-fast";
//    public static final String BASE_URL = "http://192.168.1.4:8080/renren-fast";

    public static final String LOGIN = "/app/login";
    public static final String REGISTER = "/app/register";
    public static final String VIDEO_LIST = "/app/videolist/list";//请求所有视频
    public static final String VIDEO_CATEGORY_LIST = "/app/videocategory/list";//视频类型列表
    public static final String VIDEO_LIST_BY_CATEGORY = "/app/videolist/getListByCategoryId";//各类型视频列表
    public static final String NEWS_LIST = "/app/news/api/list";//资讯列表
    public static final String VIDEO_UPDATE_COUNT = "/app/videolist/updateCount";//更新点赞,收藏,评论
    public static final String VIDEO_MYCOLLECT = "/app/videolist/mycollect";//我的收藏

    /*和风天气*/
    public static final String PUBLIC_ID = "HE2402062014001383";
    public static final String KEY = "3d4af966cd4e4a349675fa55d42b041c";
    public static final String CITY = "桑植";

    /*定位*/
    public static final long LOCATION_INTERVAL = 60 * 60 * 1000; // 定位间隔
    public static final long LOCATION_FASTEST_INTERVAL = 30 * 60 * 1000; // 定位最小间隔

    /*天气获取*/
    public static final long WEATHER_INTERVAL =30 * 60 * 1000; // 天气间隔



    public static final int PICK_IMAGE_REQUEST = 1;
    public static final int REQUEST_IMAGE_CAPTURE = 2;


    /*默认语言*/
    public static final Locale DEFAULT_LANGUAGE = LocaleContract.getSimplifiedChineseLocale();

    /*按钮错误显示时间*/
    public static final long SHOW_ERROR_DELAY = 3 * 1000;
    public static final long SHOW_NO_ERROR = 0;/*0毫秒表示不显示错误*/

    /*软件退出间隔时间*/
    public static final long DOUBLE_BACK_PRESS_TIME_INTERVAL = 2000; // 两次返回键的时间间隔


    /*存储在SharedPreferences里面的key（名字）*/

    public static final String SHARED_PREFERENCES_HEADER_PATH = "avatar_path";     /*头像*/
    public static final String SHARED_PREFERENCES_USER_NAME = "user_name";     /*网名*/
    public static final String SHARED_PREFERENCES_FACE_LOGIN = "_face_login";     /*网名*/

    public static final String SHARED_PREFERENCES_USER_LEAVE_MESSAGE = "user_leave_message"; /*留言*/

    /*room*/
    public static final int ROOM_OPERATION_INSERT = 1;     /*插入数据库*/
    public static final int ROOM_OPERATION_DELETE = 2;     /*删除数据库数据*/
    public static final int ROOM_OPERATION_UPDATE = 3;     /*更新数据库*/
    public static final int ROOM_OPERATION_QUERY = 4;     /*查询数据库*/
    public static final int ROOM_OPERATION_ALL_QUERY = 5;     /*查询全部数据库*/

    public static final String SQLITE_COLUMN_TOKEN = "token";
    public static final String SQLITE_COLUMN_USER_ADDRESS = "user_address";
    public static final String SQLITE_COLUMN_AVATAR_PATH = "avatar_path";
    public static final String SQLITE_COLUMN_USER_LANGUAGE = "user_language";
    public static final String SQLITE_COLUMN_USER_PHONE = "user_phone";
    public static final String SQLITE_COLUMN_USER_SKIN = "user_skin";
    public static final String SQLITE_COLUMN_USER_LEAVE_MESSAGE = "user_leave_message";


    public static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
            .build();
    public static final String ERNIE_BOT_ROLE_USER = "user"; //

    public static final String ERNIE_BOT_ROLE_ASSISTANT = "assistant"; //
    public static final AtomicBoolean isCancelled = new AtomicBoolean(false);

    public static final String CHART_URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro";
    public static final String CLIENT_ID = "LRZ6e4jx1KmwFkw9FjZgnqFs";
    public static final String CLIENT_SECRET = "zu6JXk02YN8TFIlsiaITFTVzrNOCT71p";
    public static final  String GRANT_TYPE = "client_credentials";
    public static final String TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token"; // 替换为实际的token端点URL
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
}
