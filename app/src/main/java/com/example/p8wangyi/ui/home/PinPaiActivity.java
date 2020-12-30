package com.example.p8wangyi.ui.home;


import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.PinPaiShouCangActivity;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.home.IPinPai;
import com.example.p8wangyi.model.home.PinPaiBean;
import com.example.p8wangyi.presenter.home.PinPaiPresenter;

import java.util.List;

public class PinPaiActivity extends BaseActivity<PinPaiPresenter> implements IPinPai.View {

    private RecyclerView mPinPaiRecv;
    private List<PinPaiBean.DataBeanX.DataBean> data;

    @Override
    protected int getLayout() {
        return R.layout.activity_pin_pai;
    }

    @Override
    protected PinPaiPresenter createPrenter() {
        return new PinPaiPresenter();
    }

    @Override
    protected void initView() {
        presenter.OnGet();
        mPinPaiRecv = findViewById(R.id.pin_pai_recv);
    }

    @Override
    protected void initData() {
        mPinPaiRecv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void OnEnter(PinPaiBean pinPaiBean) {
        data = pinPaiBean.getData().getData();
        Adapter adapter = new Adapter();
        mPinPaiRecv.setAdapter(adapter);
        adapter.setJieKou(new JieKou() {
            @Override
            public void OnClink(int post) {
                Intent intent = new Intent(PinPaiActivity.this, PinPaiShouCangActivity.class);
                intent.putExtra("id",data.get(post).getId());
                intent.putExtra("post",post);
                startActivity(intent);
            }
        });
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
        JieKou jieKou;

        public void setJieKou(JieKou jieKou) {
            this.jieKou = jieKou;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=View.inflate(PinPaiActivity.this,R.layout.activity_pinpai_item,null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.textView.setText(data.get(position).getName());
            Glide.with(PinPaiActivity.this).load(data.get(position).getApp_list_pic_url()).into(holder.img);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jieKou.OnClink(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView textView;
            public Holder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.pinpai_item_img);
                textView = itemView.findViewById(R.id.pinpai_item_text);
            }
        }
    }

    interface JieKou{
        void OnClink(int post);
    }
}