package com.example.p8wangyi.ui.topic;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseAdapter;

import java.util.List;

public class Top2Adapter extends BaseAdapter {

    public Top2Adapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_two_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopBean2.DataBeanX.DataBean bean= (TopBean2.DataBeanX.DataBean) data;
        ImageView ivtwoscencepic = (ImageView) vh.getViewById(R.id.iv_two_scence_pic);
        TextView tvtwotitle = (TextView) vh.getViewById(R.id.tv_two_title);
        TextView tvtwosubtitle = (TextView) vh.getViewById(R.id.tv_two_subtitle);
        TextView tvtwoprice = (TextView) vh.getViewById(R.id.tv_two_price);
        Glide.with(context).load(bean.getScene_pic_url()).into(ivtwoscencepic);
        tvtwoprice.setText(bean.getPrice_info()+"元起");
        tvtwosubtitle.setText(bean.getSubtitle());
        tvtwotitle.setText(bean.getTitle());
    }
}
