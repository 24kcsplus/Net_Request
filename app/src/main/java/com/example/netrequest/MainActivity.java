package com.example.netrequest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTvJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // JSON 字符串
        String jsonString = "{\n" +
                "\"data\":[\n" +
                "{\n" +
                "\"id\":6,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"面试\",\n" +
                "\"order\":1,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":9,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"Studio3\",\n" +
                "\"order\":1,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":5,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"动画\",\n" +
                "\"order\":2,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"自定义View\",\n" +
                "\"order\":3,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":2,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"性能优化 速度\",\n" +
                "\"order\":4,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":3,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"gradle\",\n" +
                "\"order\":5,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":4,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"Camera 相机\",\n" +
                "\"order\":6,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":7,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"代码混淆 安全\",\n" +
                "\"order\":7,\n" +
                "\"visible\":1\n" +
                "},\n" +
                "{\n" +
                "\"id\":8,\n" +
                "\"link\":\"\",\n" +
                "\"name\":\"逆向 加固\",\n" +
                "\"order\":8,\n" +
                "\"visible\":1\n" +
                "}\n" +
                "],\n" +
                "\"errorCode\":0,\n" +
                "\"errorMsg\":\"\"\n" +
                "}";

        JsonData jsonData = decodeJson(jsonString); // 解析 JSON 字符串
        setData(jsonData);
    }

    private void initView() {
        mTvJson = findViewById(R.id.JSON_text);
    }

    private void setData(JsonData jsonData) {
        mTvJson.setText(jsonData.toString());
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