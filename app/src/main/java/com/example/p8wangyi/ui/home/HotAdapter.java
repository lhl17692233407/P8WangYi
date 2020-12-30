package com.example.p8wangyi.ui.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.model.home.HomeBean;

import java.util.ArrayList;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.Holder> {
    private Context context;
    private ArrayList<HomeBean.DataBean.HotGoodsListBean> list;

    public HotAdapter(Context context, ArrayList<HomeBean.DataBean.HotGoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.fragment_item_hot,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView1.setText(list.get(position).getName());
        holder.textView2.setText(list.get(position).getGoods_brief());
        holder.textView3.setText("$"+list.get(position).getRetail_price());
        Glide.with(context).load(list.get(position).getList_pic_url()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView1;
        ImageView img;
        TextView textView2;
        TextView textView3;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.hot_text1);
            img = itemView.findViewById(R.id.hot_img);
            textView2 = itemView.findViewById(R.id.hot_text2);
            textView3 = itemView.findViewById(R.id.hot_text3);
        }
    }
}
