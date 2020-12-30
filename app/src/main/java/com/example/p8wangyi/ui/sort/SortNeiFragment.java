package com.example.p8wangyi.ui.sort;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseAdapter;
import com.example.p8wangyi.base.BaseFragment;
import com.example.p8wangyi.interfaces.sort.ISortNei;
import com.example.p8wangyi.model.sort.SortNeiBean;
import com.example.p8wangyi.presenter.sort.SortNeiPresenter;
import com.example.p8wangyi.presenter.sort.XiangQingActivity;

import java.util.ArrayList;
import java.util.List;

public class SortNeiFragment extends BaseFragment<SortNeiPresenter> implements ISortNei.View {
    private ImageView mNeiSortImg;
    private TextView mNeiSortText1;
    private TextView mNeiSortText2;
    private RecyclerView mSortNeiRecv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort_nei;
    }

    @Override
    protected SortNeiPresenter createPrenter() {
        return new SortNeiPresenter();
    }

    @Override
    protected void initView(View view) {

        mNeiSortImg = view.findViewById(R.id.nei_sort_img);
        mNeiSortText1 = view.findViewById(R.id.nei_sort_text1);
        mNeiSortText2 = view.findViewById(R.id.nei_sort_text2);
        mSortNeiRecv = view.findViewById(R.id.sort_nei_recv);
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        int id = arguments.getInt("id");
        presenter.OnGet(id);
    }

    @Override
    public void OnEnter(SortNeiBean sortNeiBean) {
        mNeiSortText1.setText(sortNeiBean.getData().getCurrentCategory().getFront_name());
        Glide.with(getActivity()).load(sortNeiBean.getData().getCurrentCategory().getBanner_url()).into(mNeiSortImg);
        mNeiSortText2.setText("—— " + sortNeiBean.getData().getCurrentCategory().getName() + " ——");
        mSortNeiRecv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        Adapter adapter = new Adapter(getActivity(), sortNeiBean.getData().getCurrentCategory().getSubCategoryList());
        mSortNeiRecv.setAdapter(adapter);
    }

    class Adapter extends BaseAdapter{

        public Adapter(Context context, List data) {
            super(context, data);
        }

        @Override
        protected int getLayout(int type) {
            return R.layout.sort_nei_item;
        }

        @Override
        protected void bindData(Object data, VH vh){
            SortNeiBean.DataBean.CurrentCategoryBean.SubCategoryListBean s= (SortNeiBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
            ImageView img = (ImageView) vh.getViewById(R.id.nei_item_img);
            TextView textView = (TextView) vh.getViewById(R.id.nei_item_text);
            Glide.with(context).load(s.getWap_banner_url()).into(img);
            textView.setText(s.getName());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, XiangQingActivity.class);
                    intent.putExtra("id", s.getId());
                    startActivity(intent);
                }
            });
        }
    }
}
