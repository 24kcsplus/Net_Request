package com.example.netrequest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTvJson;
    private Handler mHandler;

    private JsonData jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sendGetRequest("https://www.wanandroid.com/hotkey/json",jsonData -> {setText(jsonData);});
//        mHandler=new MyHandler();
//        if (jsonData != null) setText(jsonData);
        Log.d("onCreate","test");
    }

    private void initView() {
        mTvJson = findViewById(R.id.JSON_text);
    }

    private void setText(JsonData jsonData){
        mTvJson.setText(jsonData.toString());
    }

    private void sendGetRequest(String mUrl, JsonDataListener listener){
        //lambda表达式，相当于其中new Runnable并且重写方法
        new Thread(
                () -> {
                    try {
                        HttpURLConnection connection = getHttpURLConnection(mUrl);
                        connection.connect();//正式连接
                        InputStream in = connection.getInputStream();//从接口处获取
                        String responseData = StreamToString(in);//这里就是服务器返回的数据
                        Log.d("lx", "sendGetNetRequest: "+responseData);
                        jsonData = decodeJson(responseData); // 解析 JSON 字符串
                        if (jsonData != null){
                            runOnUiThread(() -> {
                                listener.onDataReceived(jsonData);
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    @NonNull
    private static HttpURLConnection getHttpURLConnection(String mUrl) throws IOException {
        URL url = new URL(mUrl);
        HttpURLConnection connection = (HttpURLConnection)
                url.openConnection();
        connection.setRequestMethod("GET");//设置请求方式为GET
        connection.setConnectTimeout(8000);//设置最大连接时间，单位为ms
        connection.setReadTimeout(8000);//设置最大的读取时间，单位为ms
        connection.setRequestProperty("Accept-Language",
                "zh-CN,zh;q=0.9");
        connection.setRequestProperty("Accept-Encoding",
                "gzip,deflate");
        return connection;
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            setText(decodeJson(result));
        }
    }

    private String StreamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();
        String oneLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            while ((oneLine = reader.readLine()) != null) {
                sb.append(oneLine).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private JsonData decodeJson(String data) {
        // JSON 字符串解析
        JsonData jsonData = new JsonData();
        jsonData.data = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            jsonData.errorCode = jsonObject.getInt("errorCode");
            jsonData.errorMsg = jsonObject.getString("errorMsg");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JsonData.DetailData detailData;
            for (int i = 0; i < jsonArray.length(); i++) {
                detailData = new JsonData.DetailData();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                detailData.id = jsonObject1.getInt("id");
                detailData.link = jsonObject1.getString("link");
                detailData.name = jsonObject1.getString("name");
                detailData.order = jsonObject1.getInt("order");
                detailData.visible = jsonObject1.getInt("visible");
                jsonData.data.add(detailData);
            }
        } catch (Exception e) {
            Log.e("tag", "decodeJson: ", e);
        }
        return jsonData;
    }
}