package com.example.p8wangyi.presenter.sort;

import android.content.Context;
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
import com.example.p8wangyi.interfaces.sort.IDaoNei;
import com.example.p8wangyi.model.sort.IDaoNeiBean;

import java.util.List;

public class XiangNeiFragment extends BaseFragment<IDaoNeiPresenter> implements IDaoNei.View {
    private TextView mXiangqingNeiText1;
    private TextView mXiangqingNeiItemText2;
    private RecyclerView mXiangqingNei;
    private List<IDaoNeiBean.DataBeanX.DataBean> data;
    private String name;
    private String name2;

    @Override
    protected int getLayout() {
        return R.layout.fragment_xiang_nei;
    }

    @Override
    protected IDaoNeiPresenter createPrenter() {
        return new IDaoNeiPresenter();
    }

    @Override
    protected void initView(View view) {

        mXiangqingNeiText1 = view.findViewById(R.id.xiangqing_nei_text1);
        mXiangqingNeiItemText2 = view.findViewById(R.id.xiangqing_nei_item_text2);
        mXiangqingNei = view.findViewById(R.id.xiangqing_nei);

        mXiangqingNei.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        int id = arguments.getInt("id");
        name = arguments.getString("name");
        name2 = arguments.getString("name2");
        presenter.OnGet(id);
    }

    @Override
    public void OnEnter(IDaoNeiBean iDaoNeiBean) {
        data = iDaoNeiBean.getData().getData();
        Adapter adapter = new Adapter(getActivity(), data);
        mXiangqingNei.setAdapter(adapter);
        mXiangqingNeiText1.setText(name);
        mXiangqingNeiItemText2.setText(name2);
    }

    class Adapter extends BaseAdapter{

        public Adapter(Context context, List data) {
            super(context, data);
        }

        @Override
        protected int getLayout(int type) {
            return R.layout.xiangqing_nei_item;
        }

        @Override
        protected void bindData(Object data, VH vh) {
            IDaoNeiBean.DataBeanX.DataBean id= (IDaoNeiBean.DataBeanX.DataBean) data;
            ImageView img = (ImageView) vh.getViewById(R.id.xiang_nei_item_img1);
            TextView text1 = (TextView) vh.getViewById(R.id.xiang_nei_item_text1);
            TextView text2 = (TextView) vh.getViewById(R.id.xiang_nei_item_text2);

            Glide.with(context).load(id.getList_pic_url()).into(img);
            text1.setText(id.getName());
            text2.setText("$"+id.getRetail_price()+"元");
        }
    }
}
