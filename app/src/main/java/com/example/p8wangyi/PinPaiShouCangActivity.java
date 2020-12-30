package com.example.p8wangyi;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.home.IXiangQing;
import com.example.p8wangyi.model.home.NewXiangQingBean;
import com.example.p8wangyi.model.home.XiangQingShangBean;
import com.example.p8wangyi.presenter.home.NewXiangQingPresenter;

import java.util.List;

public class PinPaiShouCangActivity extends BaseActivity<NewXiangQingPresenter> implements IXiangQing.View {
    private ImageView mShoucangImg;
    private TextView mShoucangText;
    private RecyclerView mShoucangRecv;
    private int id;
    private NewXiangQingBean.DataBean.BrandBean list;
    private TextView mShoucangText2;
    private List<XiangQingShangBean.DataBeanX.DataBean> data;
    private int post;

    @Override
    protected int getLayout() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        post = intent.getIntExtra("post", 0);
        return R.layout.activity_pin_pai_shou_cang;
    }

    @Override
    protected NewXiangQingPresenter createPrenter() {
        return new NewXiangQingPresenter();
    }

    @Override
    protected void initView() {
        presenter.OnGet(id);
        if (post == 0) {
            presenter.OnGet();
        }

        mShoucangImg = findViewById(R.id.shoucang_img);
        mShoucangText = findViewById(R.id.shoucang_text);
        mShoucangRecv = findViewById(R.id.shoucang_recv);
        mShoucangText2 = findViewById(R.id.shoucang_text2);
    }

    @Override
    protected void initData() {
        mShoucangRecv.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void OnEnter(NewXiangQingBean data) {
        list = data.getData().getBrand();
        Glide.with(PinPaiShouCangActivity.this).load(list.getApp_list_pic_url()).into(mShoucangImg);
        mShoucangText.setText(list.getName());
        mShoucangText2.setText(list.getSimple_desc());
    }

    @Override
    public void OnEnter(XiangQingShangBean data2) {
        data = data2.getData().getData();
        Adapter adapter = new Adapter();
        mShoucangRecv.setAdapter(adapter);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @NonNull
        @Override
        public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(PinPaiShouCangActivity.this, R.layout.activity_xiangqing_item, null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
            TextView textView = holder.textView;
            textView.setText(data.get(position).getName());
            holder.textView2.setText("$" + data.get(position).getRetail_price() + "元");
            Glide.with(PinPaiShouCangActivity.this).load(data.get(position).getList_pic_url()).into(holder.img);

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView textView;
            TextView textView2;

            public Holder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.xiangqing_item_img);
                textView = itemView.findViewById(R.id.xiangqing_item_text1);
                textView2 = itemView.findViewById(R.id.xiangqing_item_text2);
            }
        }
    }
}