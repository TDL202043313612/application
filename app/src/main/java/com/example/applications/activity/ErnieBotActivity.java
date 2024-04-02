package com.example.applications.activity;


import static com.example.applications.ai.AiMessage.SEND_BY_BOT;
import static com.example.applications.ai.AiMessage.SEND_BY_ME;
import static com.example.applications.ai.AiMessage.StreamMessage.string_end;
import static com.example.applications.ai.AiMessage.StreamMessage.string_start;
import static com.example.applications.ai.AiMessage.StreamMessage.string_underway;
import static com.example.applications.api.ApiConfig.CHART_URL;
import static com.example.applications.api.ApiConfig.CLIENT_ID;
import static com.example.applications.api.ApiConfig.CLIENT_SECRET;
import static com.example.applications.api.ApiConfig.DOUBLE_BACK_PRESS_TIME_INTERVAL;
import static com.example.applications.api.ApiConfig.ERNIE_BOT_ROLE_ASSISTANT;
import static com.example.applications.api.ApiConfig.ERNIE_BOT_ROLE_USER;
import static com.example.applications.api.ApiConfig.GRANT_TYPE;
import static com.example.applications.api.ApiConfig.JSON;
import static com.example.applications.api.ApiConfig.TOKEN_URL;
import static com.example.applications.api.ApiConfig.client;
import static com.example.applications.api.ApiConfig.isCancelled;
import static com.example.applications.util.StringUtils.isEmpty;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applications.R;
import com.example.applications.adapter.AiMessageAdapter;
import com.example.applications.ai.AiMessage;
import com.example.applications.entity.AiMessageResponse;
import com.example.applications.util.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.gyf.immersionbar.ImmersionBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ErnieBotActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ImageButton messageImageButton;
    private EditText messageEditText;
    private Context context = this;
    private List<AiMessage> messageList;
    private AiMessageAdapter messageAdapter;
    private TextView welcomeTextView;
    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout mainLayout;

    private boolean ifStart;
    private String message;
    private AiMessageResponse aiMessageResponse;

    private long lastBackPressedTime = 0;

    private JSONArray jsonArray = new JSONArray();
    private String completeMessage;

    private Call tokenCall,streamCall;
    private boolean currentOkhttpState=false;
    private boolean messyCode = false;
    private int enterEndCount = 0;

    @Override
    protected int initLayout() {
        return R.layout.activity_erniebot;
    }

    @Override
    protected void initView() {
        mainLayout = findViewById(R.id.main_layout);
        recyclerView = findViewById(R.id.recycler_view);
        messageImageButton = findViewById(R.id.message_image_button);
        messageEditText = findViewById(R.id.message_edit_text);
        welcomeTextView = findViewById(R.id.welcome_text);
    }

    @Override
    protected void initData() {

        setOnClickListener(messageImageButton);
        messageList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        messageAdapter = new AiMessageAdapter(context);
        recyclerView.setAdapter(messageAdapter);

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
                } else if (dy < 0) {
                    // 检查是否从顶部开始滑动
                    if (lastVisibleItemPosition == 0) {
                        // 向上滑动并且位于顶部逻辑
                        Log.d("RecyclerView", "从顶部向上滑动");
                    } else {
                        // 向上滑动但不是从顶部开始逻辑
                        hideKeyboard();
                    }
                }
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });
        /*状态栏和导航栏颜色初始化*/
        initStatusAndNavigation();
    }
//    public Bitmap stringToBitmap(String string) {
//        Bitmap bitmap = null;
//        try {
//            byte[] bitmapArray = Base64.decode(getString(R.string.base65_image).split(",")[1], Base64.DEFAULT);
//            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }



    private void limitJsonArrayLength() {
        int totalLength=0;
        for (int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = null;
            Object contentObj=null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                contentObj = jsonObject.get("content");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // 检查是否为String类型
            if (contentObj instanceof String) {
                String content = (String) contentObj;
                // 获取字符串长度
                totalLength += content.length();
            }

        }

        while(totalLength >= 20000){
            try {
                totalLength -= jsonArray.getJSONObject(0).get("content")
                                                                .toString()
                                                                .length();
                totalLength -= jsonArray.getJSONObject(1).get("content")
                                                                .toString()
                                                                .length();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.remove(0);
            jsonArray.remove(1);
        }

    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBackPressedTime< DOUBLE_BACK_PRESS_TIME_INTERVAL) {
            // 如果两次按返回键的时间间隔小于预定义的时间间隔，则退出应用程序
            // 关闭整个应用程序
            finishAffinity();
        } else {
            // 否则更新上一次按返回键的时间，并显示提示信息
            lastBackPressedTime = currentTime;
            Toast.makeText(context,"再按一次退出",Toast.LENGTH_SHORT).show();
        }
    }
    private void initStatusAndNavigation(){
        ImmersionBar.with(this)
                .keyboardEnable(true)
                // 默认状态栏字体颜色为黑色
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                // 指定导航栏背景颜色
                .navigationBarColor(R.color.white)
                .statusBarColor(R.color.white)
                // 状态栏字体和导航栏内容自动变色，必须指定状态栏颜色和导航栏颜色才可以自动变色
                .autoDarkModeEnable(true, 0.2f)
                .init();
    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void messageSend(String message, String sentBy, AiMessage.StreamMessage streamMessage){
        if ((!isEmpty(sentBy) && sentBy.equals(SEND_BY_ME)) ||
                (!isEmpty(sentBy) && sentBy.equals(SEND_BY_BOT)&&
                        streamMessage == string_start)){
            AiMessage message1 = new AiMessage(message, sentBy , streamMessage);
            messageList.add(message1);
        }else{ // else if (streamMessage == string_underway || streamMessage == string_end)
            messageList.get(messageList.size()-1).setMessage(messageList.get(messageList.size()-1).getMessage()+message);
            messageList.get(messageList.size()-1).setStream(streamMessage);
        }


        messageAdapter.setMMessageList(messageList);
        //提示数据更新
        messageAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
    }
    private void addToChat(String message){
        messageSend(message, SEND_BY_ME,string_start);
    }



    private void addResponse(String message, AiMessage.StreamMessage streamMessage){
        messageSend(message, SEND_BY_BOT,streamMessage);
    }

    public  void postRequest2(String message){

        FormBody.Builder param = new FormBody.Builder();
        param.add("client_id",CLIENT_ID);
        param.add("client_secret",CLIENT_SECRET);
        param.add("grant_type",GRANT_TYPE);


        Request request = new Request.Builder()
                .url(TOKEN_URL)
                .addHeader("Content-Type", "application/json")
                .post(param.build())
                .build();

        tokenCall=client.newCall(request);
        tokenCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 网络请求失败时的处理
                Log.d("test",""+e);
                e.printStackTrace();
                messageImageButton.setBackgroundResource(R.drawable.ic_baseline_send_24);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 网络请求成功时的处理

                if (response.isSuccessful()) {
                    String responseData = response.body().string().trim();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        completeMessage="";
                        messyCode = false;
                        enterEndCount = 0;
                        postStreamRequest(message,jsonObject.get("access_token").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                }
            }
        });
    }
    public void postStreamRequest(String message,String token){

        FormBody.Builder param = new FormBody.Builder();
        param.add("access_token",token);


        JSONObject jsonObject = new JSONObject();;

        JSONObject messageObject = new JSONObject();

        jsonArray.put(messageObject);
        limitJsonArrayLength();
        try {
            messageObject.put("role", ERNIE_BOT_ROLE_USER);
            messageObject.put("content", message);
            jsonObject.put("messages",jsonArray);
            jsonObject.put("stream",true);
            Log.d("test",jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 将 JSONObject 转换为 JSON 字符串（前端和后端传输只能是字符串形式）
        String jsonString = jsonObject.toString();
        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder()
                .url(CHART_URL+"?access_token="+token)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        // 发起异步 POST 请求
        streamCall=client.newCall(request);
        streamCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                errorClose();
                // 网络请求失败时的处理
                Log.d("test","chat onFailure");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 网络请求成功时的处理
                if (!response.isSuccessful()) {
                    errorClose();
                    return;
                }
                    ResponseBody responseBody = response.body();
                    if (null != responseBody) {
                        try (InputStream inputStream = responseBody.byteStream()) {

                            byte[] buffer = new byte[1024];
                            int len =0;
                            while ((len = inputStream.read(buffer)) != -1) {
                                String str = new String(buffer,0,len, Charset.defaultCharset());
                                str.replaceAll(" ",""); // 去除所有空格

                                String jsonWithoutPrefix = str.replaceFirst("data: ", "");
                                Log.i("test", "streamChat inputStream.read " + jsonWithoutPrefix);
                                aiMessageResponse = new AiMessageResponse();
                                JSONObject jsonObject = new JSONObject();
                                boolean isEnd = false;

                                try {
                                    aiMessageResponse = new Gson().fromJson(jsonWithoutPrefix,AiMessageResponse.class);
                                    jsonObject = new JSONObject(jsonWithoutPrefix);
                                    isEnd = jsonObject.getBoolean("is_end");
                                }catch (Exception e){
                                    JsonReader jsonReader = new JsonReader(new StringReader(jsonWithoutPrefix));//其中jsonContext为String类型的Json数据
                                    jsonReader.setLenient(true);
                                    JsonElement jsonElement = JsonParser.parseReader(jsonReader); // 使用JsonParser将JsonReader转换为JsonElement
                                    aiMessageResponse = new Gson().fromJson(jsonElement,AiMessageResponse.class);
                                    isEnd = true;

                                }

                                completeMessage += aiMessageResponse.getResult();
                                Log.d("test", String.valueOf(isEnd));
                                if (!isCancelled.get()){
                                    if (!isEnd) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (ifStart){
                                                    ifStart = false;
                                                    addResponse(aiMessageResponse.getResult(),string_start);
                                                }else{
                                                    addResponse(aiMessageResponse.getResult(),string_underway);
                                                }
                                            }
                                        });

                                    }else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(context,"1 saveEndData",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        saveEndData();
                                        if (enterEndCount == 2){
                                            messyCode = true;
                                            enterEndCount = 0;
                                        }else {
                                            enterEndCount++;
                                        }
                                    }
                                }else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context,"2 saveEndData",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    saveEndData();
                                }
                            }
                            if (messyCode){
                                handleErrorArray();
                            }

                        } catch (IOException ioException) {
                            Log.i("test", "streamChat responseBody 2");
                            ioException.printStackTrace();
                            saveEndData();
                        }
                    }



            }
            private void handleErrorArray(){
                Log.d("test","messyCode :"+jsonArray);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"messyCode",Toast.LENGTH_SHORT).show();
                    }
                });
                int arraySize = jsonArray.length();
                Object contentObj_1=null;
                Object contentObj_2=null;
                try {
                    contentObj_1 = jsonArray.getJSONObject(arraySize-1).get("content");
                    contentObj_2 = jsonArray.getJSONObject(arraySize-2).get("content");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // 检查是否为String类型
                if (contentObj_1 instanceof String && contentObj_2 instanceof String) {
                    String content_1 = (String) contentObj_1;
                    String content_2 = (String) contentObj_2;
                    if (content_1.length() > content_2.length()){
                        jsonArray.remove(arraySize-2);
                    }else {
                        jsonArray.remove(arraySize-1);

                    }

                }
            }
            private void saveEndData() {
                // 实现你的数据保存逻辑

                JSONObject messageObject = new JSONObject();
                try {
                    messageObject.put("role",ERNIE_BOT_ROLE_ASSISTANT);
                    messageObject.put("content",completeMessage);
                    jsonArray.put(messageObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendEndData();
            }

            private void errorClose(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"errorClose",Toast.LENGTH_SHORT).show();

                    }
                });
                /*当okhttp发送失败（fail）、不成功等等，说明这次问题解答不成，需要把jsonArray里面当问题删除调，否则后面报错*/
                sendEndData();
                jsonArray.remove(jsonArray.length()-1);

            }

            private void sendEndData(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addResponse(aiMessageResponse.getResult(),string_end);
                        messageImageButton.setBackgroundResource(R.drawable.ic_baseline_send_24);
                    }
                });
            }

        });

    }


    @Override
    public void onClick(View view) {
        if (view == messageImageButton){
            String message = messageEditText.getText().toString();
            if (currentOkhttpState == false){
                if (!message.equals("")){
                    currentOkhttpState = true;
                    messageEditText.setText("");
                    addToChat(message);
                    welcomeTextView.setVisibility(View.GONE);
                    isCancelled.set(false);
                    ifStart = true;
                    messageImageButton.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24);
                    postRequest2(message);
                }
            }else {
                currentOkhttpState = false;
                messageImageButton.setBackgroundResource(R.drawable.ic_baseline_send_24);
                isCancelled.set(true);
                tokenCall.cancel();
                streamCall.cancel();
            }


        }
    }


}