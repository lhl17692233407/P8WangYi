package com.example.p8wangyi.ui.home;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.home.IZhouYi;
import com.example.p8wangyi.model.home.TuPianBean;
import com.example.p8wangyi.model.home.ZhouYiBean;
import com.example.p8wangyi.presenter.home.ZhouYiPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZhouYiActivity extends BaseActivity<ZhouYiPresenter> implements IZhouYi.View, View.OnClickListener {
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";

    private ImageView mZhouyiImg;
    private TextView mZhouyiText;
    private TextView mZhouyiText1;
    private TextView mZhouyiText2;
    private ImageView mImgArrowUp;
    private ImageView mImgArrowDown;
    private LinearLayout mLayoutPrice;
    private TextView mZhouyiText3;
    private ConstraintLayout mLayoutSort;
    private RecyclerView mZhouyiRecv;
    private String order;
    private String sort;
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private int categoryId;
    private List<ZhouYiBean.DataBeanX.DataBean> zhouyi;
    private Adapter adapter;
    private ArrayList<ZhouYiBean.DataBeanX.DataBean> dataBeans;
    private List<ZhouYiBean.DataBeanX.FilterCategoryBean> filterCategory;
    private PopupWindow popupWindow;
    private int aaa;
    private int bbb=0;

    @Override
    protected int getLayout() {
        return R.layout.activity_zhou_yi;
    }

    @Override
    protected ZhouYiPresenter createPrenter() {
        return new ZhouYiPresenter();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        mZhouyiImg = findViewById(R.id.zhouyi_img);
        mZhouyiText = findViewById(R.id.zhouyi_text);
        mZhouyiText1 = findViewById(R.id.zhouyi_text1);
        mZhouyiText2 = findViewById(R.id.zhouyi_text2);
        mImgArrowUp = findViewById(R.id.img_arrow_up);
        mImgArrowDown = findViewById(R.id.img_arrow_down);
        mLayoutPrice = findViewById(R.id.layout_price);
        mZhouyiText3 = findViewById(R.id.zhouyi_text3);
        mLayoutSort = findViewById(R.id.layout_sort);
        mZhouyiRecv = findViewById(R.id.zhouyi_recv);

        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        mLayoutPrice.setTag(0);
        mZhouyiText1.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.red)));

    }

    @Override
    protected void initData() {
        mZhouyiText1.setOnClickListener(this);
        mLayoutPrice.setOnClickListener(this);
        mZhouyiText3.setOnClickListener(this);

        presenter.OnGet();
        presenter.OnGet(getParm());

        mZhouyiRecv.setLayoutManager(new GridLayoutManager(ZhouYiActivity.this,2));
        dataBeans = new ArrayList<>();
        adapter = new Adapter(dataBeans);
        mZhouyiRecv.setAdapter(adapter);
    }

    @Override
    public void OnEnter(ZhouYiBean zhouYiBean) {
        filterCategory = zhouYiBean.getData().getFilterCategory();
        dataBeans.clear();
        zhouyi = zhouYiBean.getData().getData();
        dataBeans.addAll(zhouyi);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnEnter(TuPianBean tupian) {
        TuPianBean.DataBean.BannerInfoBean list = tupian.getData().getBannerInfo();
        Glide.with(ZhouYiActivity.this).load(list.getImg_url()).into(mZhouyiImg);
        mZhouyiText.setText(list.getName());
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_price:
                int tag = (int) mLayoutPrice.getTag();
                if (tag==0){
                    resetPriceState();
                    priceStateUp();
                    mLayoutPrice.setTag(1);
                    order = ASC;
                }else if (tag==1){
                    resetPriceState();
                    priceStateDown();
                    mLayoutPrice.setTag(0);
                    order = DESC;
                }
                sort = PRICE;
                presenter.OnGet(getParm());
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
            case R.id.zhouyi_text1:
                resetPriceState();
                mZhouyiText1.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.red)));
                sort = DEFAULT;
                categoryId = 0;
                presenter.OnGet(getParm());
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
            case R.id.zhouyi_text3:
                resetPriceState();
                mZhouyiText3.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.red)));
                //pop();
                setPopw();
                break;
        }
    }
    private void setPopw() {
        Resources resources = getResources();
        //PopWindow条目布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.popw, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获取条目id
        LinearLayout li1 = inflate.findViewById(R.id.li1);
        //设置条目边框宽高
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        //循环放入条目
        for (int i = 0; i < filterCategory.size(); i++) {
            View inflate1 = LayoutInflater.from(this).inflate(R.layout.popw_item, null);
            TextView tv = inflate1.findViewById(R.id.item_tv);
            tv.setText(filterCategory.get(i).getName());
            tv.setLayoutParams(layoutParams);
            //文字设为居中
            tv.setGravity(Gravity.CENTER);
            int finalI = i;
            tv.setTag(i);

            inflate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ZhouYiBean.DataBeanX.FilterCategoryBean filterCategoryBean = filterCategory.get(finalI);
                    categoryId=filterCategoryBean.getId();
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("categoryId",filterCategoryBean.getId()+"");
                    stringStringHashMap.put("isNew",1+"");
                    presenter.OnGet(stringStringHashMap);
                    popupWindow.dismiss();
                }
            });
            //给边框设置颜色
            Drawable drawable = resources.getDrawable(R.drawable.stroke_black);
            tv.setBackground(drawable);
            //添加view的Textview的条目
            li1.addView(tv);
        }
        //绑定popwindow
        popupWindow.showAsDropDown(mLayoutPrice,0,10);
    }
    @SuppressLint("ResourceType")
    private void pop() {

        View view=View.inflate(ZhouYiActivity.this,R.layout.zhouyi_pop,null);
        LinearLayout mLin = view.findViewById(R.id.pop_lin);
        LinearLayout mLin2 = view.findViewById(R.id.pop_lin2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,300);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAsDropDown(view,0,800);
//        mLin.setLayoutParams(layoutParams);
//        mLin2.setLayoutParams(layoutParams);
        Log.e("tag",filterCategory.size()+"textview的大小");
        ArrayList<TextView> textViews=new ArrayList<>();
        for (int i = 0; i < filterCategory.size(); i++) {
            TextView textView = new TextView(ZhouYiActivity.this);
            textViews.add(textView);
            textView.setText(filterCategory.get(i).getName());
            textView.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.black)));
            textView.setLayoutParams(layoutParams);
            if (i>4){
                mLin2.addView(textView);
            }else{
                mLin.addView(textView);
                textView.setBackgroundResource(R.drawable.pop_kuang);
                textView.setGravity(Gravity.CENTER);
                textView.setLeft(5);
            }

        }
        for (int i = 0; i < textViews.size(); i++) {
            int finalI = i;
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bbb = 1;
                    aaa = filterCategory.get(finalI).getId();
                    textViews.get(finalI).setBackgroundResource(R.drawable.pop_kuang_no);
                    popupWindow.dismiss();
                    categoryId=aaa;
                    presenter.OnGet(getParm());
                }
            });
        }
    }

    private HashMap<String, String> getParm() {
        HashMap<String, String> map = new HashMap<>();
        map.put("isNew",String.valueOf(isNew));
        map.put("page",String.valueOf(page));
        map.put("size",String.valueOf(size));
        map.put("order",order);
        map.put("sort",sort);
        map.put("category",String.valueOf(categoryId));
        return map;
    }

    //按照价格的将序排序
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        mImgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        mImgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        mZhouyiText2.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.red)));
    }

    //按照价格生序排序
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        mImgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        mImgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        mZhouyiText2.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.red)));
    }

    //重置状态
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        mImgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        mImgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        mZhouyiText1.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.black)));
        mZhouyiText2.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.black)));
        mZhouyiText3.setTextColor(Color.parseColor(ZhouYiActivity.this.getString(R.color.black)));
        mLayoutPrice.setTag(0);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

        ArrayList<ZhouYiBean.DataBeanX.DataBean> zhou;

        public Adapter(ArrayList<ZhouYiBean.DataBeanX.DataBean> dataBeans) {
            zhou = dataBeans;
        }

        @NonNull
        @Override
        public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=View.inflate(ZhouYiActivity.this,R.layout.activity_zhouyi_item,null);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
            holder.textView1.setText(zhou.get(position).getName());
            holder.textView2.setText("$"+zhou.get(position).getRetail_price()+"元");
            Glide.with(ZhouYiActivity.this).load(zhou.get(position).getList_pic_url()).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return zhou.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView textView1;
            TextView textView2;
            public Holder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.zhouyi_item_img);
                textView1 = itemView.findViewById(R.id.zhouyi_item_text1);
                textView2 = itemView.findViewById(R.id.zhouyiitem_text2);
            }
        }
    }
}