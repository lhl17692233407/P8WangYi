package com.example.p8wangyi.ui.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.IBasePresenter;

public class PingLunActivity extends BaseActivity {

    private RecyclerView mPinglunRecv;

    @Override
    protected int getLayout() {
        return R.layout.activity_ping_lun;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        mPinglunRecv = findViewById(R.id.pinglun_recv);

        mPinglunRecv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {

    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class Holder extends RecyclerView.ViewHolder {

            public Holder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}