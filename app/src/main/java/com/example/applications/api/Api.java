package com.example.applications.api;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.applications.activity.LoginActivity;
import com.example.applications.callback.TtitCallback;
import com.example.applications.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//在上述代码中，Api 类的构造方法被声明为私有的，即 private Api() {}，
// 这意味着在类的外部无法直接使用 new Api() 来实例化对象。
public class Api {
    public static Api api =null;
    private static String requestUrl;
    private static HashMap<String,Object> mParams;
    private static OkHttpClient client;

    //静态函数在不创建类的实例的情况下就可以被调用（new Api()这步操作可以不用执行）
    public static Api config(String url, HashMap<String,Object> params){
        if (api==null){
            synchronized (Api.class){
                if (api==null){
                    api=new Api();
                    // 创建 OkHttpClient 实例
                    client = new OkHttpClient();
                }
            }
        }
        requestUrl = ApiConfig.BASE_URL+url;
        mParams = params;
        return api;
    }
    public static void postRequest(Context context, TtitCallback callback){
        SharedPreferences sp = context.getSharedPreferences("sp_ttit", MODE_PRIVATE);
        String token=sp.getString("token","");
        //将hashmap 转换成 jsonobject
        JSONObject jsonRequest = new JSONObject(mParams);
        // 将 JSONObject 转换为 JSON 字符串（前端和后端传输只能是字符串形式）
        String jsonString = jsonRequest.toString();
        // 设置请求体为 JSON 数据
        RequestBody requestBody = RequestBody.create(jsonString,
                MediaType.parse("application/json; charset=utf-8"));

        // 创建请求对象，指定请求方式为 POST，设置请求体
        Request request = new Request.Builder()
                .url(requestUrl) // 替换为实际的后端 API 地址
                .addHeader("contentType", "application/json; charset=UTF-8")
                .addHeader("token",token)
                .post(requestBody)
                .build();

        // 发起异步 POST 请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 网络请求失败时的处理
//                e.printStackTrace();
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 网络请求成功时的处理
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    callback.onSuccess(responseData);
                }
            }
        });
    }

    public void getRequest(Context context, TtitCallback callback){
        // 构建带有查询参数的 URL
        String url = getAppendUrl(requestUrl,mParams);
        SharedPreferences sp = context.getSharedPreferences("sp_ttit", MODE_PRIVATE);
        String token=sp.getString("token","");
        if (!StringUtils.isEmpty(token)){
            // 构建 GET 请求
            Request request = new Request.Builder()
                    .url(url)
                    .header("token",token)
                    .get()
                    .build();

            // 发起异步 POST 请求
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // 网络请求失败时的处理
//                e.printStackTrace();
                    callback.onFailure(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        if (code.equals("401")){
                            Intent in = new Intent(context, LoginActivity.class);
                            context.startActivity(in);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // 网络请求成功时的处理
                    if (response.isSuccessful()) {
//                        final String responseData = response.body().string();
                        callback.onSuccess(result);
                    }
                }
            });
        }else {
            Intent in = new Intent(context, LoginActivity.class);
            context.startActivity(in);
        }

    }

    private String getAppendUrl(String url, Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (StringUtils.isEmpty(buffer.toString())) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }
                buffer.append(entry.getKey()).append("=").append(entry.getValue());
            }
            url += buffer.toString();
        }
        return url;
    }
}
