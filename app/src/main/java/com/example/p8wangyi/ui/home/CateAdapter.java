package com.example.p8wangyi.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.model.home.GouMaiActivity;
import com.example.p8wangyi.model.home.HomeBean;

import java.util.ArrayList;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.Holder> {
    private ArrayList<HomeBean.DataBean.CategoryListBean> list;
    private Context context;
    private Activity gouMaiActivity;

    public CateAdapter(Activity gouMaiActivity,ArrayList<HomeBean.DataBean.CategoryListBean> list, Context context) {
        this.list = list;
        this.gouMaiActivity = gouMaiActivity;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.fragment_item_cate,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        Adapter2 adapter2 = new Adapter2(gouMaiActivity,(ArrayList<HomeBean.DataBean.CategoryListBean.GoodsListBean>) list.get(position).getGoodsList(), context);
        holder.recyclerView.setAdapter(adapter2);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cate_text);
            recyclerView = itemView.findViewById(R.id.cate_recv);
        }
    }

    class Adapter2 extends RecyclerView.Adapter<Adapter2.Holder1>{
        private ArrayList<HomeBean.DataBean.CategoryListBean.GoodsListBean> listBeans;
        private Context context;
        private Activity gouMaiActivity;

        public Adapter2(Activity gouMaiActivity,ArrayList<HomeBean.DataBean.CategoryListBean.GoodsListBean> listBeans, Context context) {
            this.listBeans = listBeans;
            this.context = context;
            this.gouMaiActivity = gouMaiActivity;
        }

        @NonNull
        @Override
        public Adapter2.Holder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=View.inflate(context,R.layout.fragment_cate_cate4,null);
            return new Holder1(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter2.Holder1 holder, int position) {
            holder.textView.setText(listBeans.get(position).getName());
            holder.textView2.setText("$"+listBeans.get(position).getRetail_price()+"元");
            Glide.with(context).load(listBeans.get(position).getList_pic_url()).into(holder.img);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GouMaiActivity.class);
                    intent.putExtra("id",listBeans.get(position).getId());
                    intent.putExtra("img",listBeans.get(position).getList_pic_url());
                    gouMaiActivity.startActivityForResult(intent,1);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listBeans.size();
        }

        public class Holder1 extends RecyclerView.ViewHolder {
            ImageView img;
            TextView textView;
            TextView textView2;
            public Holder1(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.cate_cate_img);
                textView = itemView.findViewById(R.id.cate_cate_text);
                textView2 = itemView.findViewById(R.id.cate_cate_text2);
            }
        }
    }
}
