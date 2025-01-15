package com.example.netrequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<JsonData.DetailData> jsonDataDetailArrayList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.jsondata_rv, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 根据position绑定数据
        JsonData.DetailData jsonDataDetail = jsonDataDetailArrayList.get(position);

        holder.getmTvJsondataRvId().setText("id:" + jsonDataDetail.id);
        holder.getmTvJsondataRvLink().setText("link:\" " + jsonDataDetail.link + " \"");
        holder.getmTvJsondataRvName().setText("name:" + jsonDataDetail.name);
        holder.getmTvJsondataRvOrder().setText("order:" + jsonDataDetail.order);
        holder.getmTvJsondataRvVisible().setText("visible:" + jsonDataDetail.visible);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvJsondataRvId;
        private TextView mTvJsondataRvLink;
        private TextView mTvJsondataRvName;
        private TextView mTvJsondataRvOrder;
        private TextView mTvJsondataRvVisible;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvJsondataRvId = itemView.findViewById(R.id.tv_jsondata_rv_id);
            mTvJsondataRvLink = itemView.findViewById(R.id.tv_jsondata_rv_link);
            mTvJsondataRvName = itemView.findViewById(R.id.tv_jsondata_rv_name);
            mTvJsondataRvOrder = itemView.findViewById(R.id.tv_jsondata_rv_order);
            mTvJsondataRvVisible = itemView.findViewById(R.id.tv_jsondata_rv_visible);
        }

        public TextView getmTvJsondataRvId() {
            return mTvJsondataRvId;
        }

        public TextView getmTvJsondataRvLink() {
            return mTvJsondataRvLink;
        }

        public TextView getmTvJsondataRvName() {
            return mTvJsondataRvName;
        }

        public TextView getmTvJsondataRvOrder() {
            return mTvJsondataRvOrder;
        }

        public TextView getmTvJsondataRvVisible() {
            return mTvJsondataRvVisible;
        }
    }

    public DataAdapter(ArrayList<JsonData.DetailData> dataList) {
        this.jsonDataDetailArrayList = dataList;
    }

    public JsonData.DetailData getItem(int position) {
        return jsonDataDetailArrayList.get(position);
    }

    public int getItemCount() {
        return jsonDataDetailArrayList.size();
    }

}
