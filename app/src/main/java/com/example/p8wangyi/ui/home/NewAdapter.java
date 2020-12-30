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

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.Holder> {
    private Context context;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> list;

    public NewAdapter(Context context, ArrayList<HomeBean.DataBean.NewGoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.fragment_item_new,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView1.setText(list.get(position).getName());
        holder.textView2.setText("$"+list.get(position).getRetail_price());
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
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.new_text1);
            img = itemView.findViewById(R.id.new_img);
            textView2 = itemView.findViewById(R.id.new__text1);
        }
    }
}
