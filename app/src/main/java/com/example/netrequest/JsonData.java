package com.example.netrequest;

import android.os.Build;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonData {

    // 定义 JSON 数据结构

    public ArrayList<DetailData> data;
    public int errorCode;
    public String errorMsg;

    public static class DetailData {
        public int id;
        public String link;
        public String name;
        public int order;
        public int visible;
    }

    @NonNull
    @Override
    public String toString() {
        // 重写 toString 方法，返回 JSON 数据
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return "JsonData{" +
                    "errorCode=" + errorCode +
                    ", errorMsg=\"" + errorMsg + '\"' +
                    ", data=" + data.stream()
                    .map(detailData -> "{" +
                            "id=" + detailData.id +
                            ", link=\"" + detailData.link + '\"' +
                            ", name=\"" + detailData.name + '\"' +
                            ", order=" + detailData.order +
                            ", visible=" + detailData.visible +
                            "}")
                    .collect(Collectors.joining(", ")) +
                    "}";
        } else {
            return "JsonData{" +
                    "errorCode=" + errorCode +
                    ", errorMsg=\"" + errorMsg + '\"' +
                    ", dataID=" + data.get(0).id +
                    ", dataLink=\"" + data.get(0).link + '\"' +
                    ", dataName=\"" + data.get(0).name + '\"' +
                    ", dataOrder=" + data.get(0).order +
                    ", dataVisible=" + data.get(0).visible +
                    "}";
        }
    }
}
