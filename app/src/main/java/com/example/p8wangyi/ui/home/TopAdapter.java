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

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.Holder> {
    private Context context;
    private ArrayList<HomeBean.DataBean.TopicListBean> list;

    public TopAdapter(Context context, ArrayList<HomeBean.DataBean.TopicListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.fragment_item_top,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView1.setText(list.get(position).getTitle());
        holder.textView2.setText("$"+list.get(position).getPrice_info()+"元起");
        Glide.with(context).load(list.get(position).getScene_pic_url()).into(holder.img);
        holder.textView3.setText(list.get(position).getSubtitle());
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
            textView1 = itemView.findViewById(R.id.top_text1);
            img = itemView.findViewById(R.id.top_img);
            textView2 = itemView.findViewById(R.id.top_text2);
            textView3 = itemView.findViewById(R.id.top_text3);
        }
    }
}
