package com.example.netrequest;

import androidx.annotation.NonNull;

import java.util.List;

public class JsonData {

    // 定义 JSON 数据结构

    public List<DetailData> data;
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
