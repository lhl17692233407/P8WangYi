package com.example.p8wangyi.ui.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.base.BaseAdapter;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.utils.RealmUser;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ShouCangActivity extends BaseActivity {


    private RecyclerView mRec;
    private Realm mRelam;

    @Override
    protected int getLayout() {
        return R.layout.activity_shou_cang;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        mRec = findViewById(R.id.recv_shoucang);
        mRec.setLayoutManager(new LinearLayoutManager(ShouCangActivity.this));
        mRelam = Realm.getDefaultInstance();
        RealmResults<RealmUser> users = mRelam.where(RealmUser.class).findAll();

        Adapter adapter = new Adapter( users,ShouCangActivity.this);
        mRec.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        private RealmResults<RealmUser> users;

        private Context context;

        public Adapter(RealmResults<RealmUser> users, Context context) {
            this.users = users;
            this.context = context;
        }

        @NonNull
        @Override
        public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=View.inflate(context,R.layout.shoucang_item,null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
            holder.textView1.setText(users.get(position).getName());
            holder.textView2.setText("￥"+users.get(position).getPrice()+"元");
            Glide.with(context).load(users.get(position).getImg()).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView textView1;
            TextView textView2;

            public Holder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.shoucang_item_img);
                textView1 = itemView.findViewById(R.id.shoucang_item_text1);
                textView2 = itemView.findViewById(R.id.shoucang_item_text2);
            }
        }
    }
}