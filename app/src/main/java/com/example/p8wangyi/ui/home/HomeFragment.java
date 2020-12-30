package com.example.p8wangyi.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p8wangyi.R;
import com.example.p8wangyi.TiaoZhuanActivity;
import com.example.p8wangyi.base.BaseFragment;
import com.example.p8wangyi.interfaces.home.IHome;
import com.example.p8wangyi.model.home.HomeBean;
import com.example.p8wangyi.presenter.home.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {

    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    private Banner mBanner;
    private LinearLayout mLayoutTab;
    private TextView mTxtBrandTitle;
    private RecyclerView mRecyBrand;
    private TextView mTxtNewgoodTitle;
    private RecyclerView mRecyNewgood;
    private RecyclerView mRecyHot;
    private LinearLayout mLin;
    private RecyclerView mRecTop;
    private RecyclerView mRecCate;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPrenter() {
        Log.e("tag","111111111111111");
        return new HomePresenter();
    }

    @Override
    protected void initView(View view) {
        mBanner = view.findViewById(R.id.banner);
        mLayoutTab = view.findViewById(R.id.layout_tab);
        mTxtBrandTitle = view.findViewById(R.id.txt_brand_title);
        mRecyBrand = view.findViewById(R.id.recy_brand);
        mTxtNewgoodTitle = view.findViewById(R.id.txt_newgood_title);
        mRecyNewgood =view. findViewById(R.id.recy_newgood);
        mRecyHot = view.findViewById(R.id.home_recv_hot);
        mLin = view.findViewById(R.id.home_lin);
        mRecTop = view.findViewById(R.id.home_recv_top);
        mRecCate = view.findViewById(R.id.home_recv_cate);

        mRecyBrand.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyNewgood.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecTop.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        mRecCate.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTxtNewgoodTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ZhouYiActivity.class));
            }
        });

        mTxtBrandTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PinPaiActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getHome(); //初始化加载数据
    }

    @Override
    public void getHomeReturn(HomeBean result) {
        if (result.getData() != null) {
            initBanner(result.getData().getBanner());
            initChannel(result.getData().getChannel());
            initBrand(result.getData().getBrandList());
            initNew(result.getData().getNewGoodsList());
            initHot(result.getData().getHotGoodsList());
            initTop(result.getData().getTopicList());
            initCate(result.getData().getCategoryList());
        }
    }

    private void initCate(List<HomeBean.DataBean.CategoryListBean> categoryList) {
        FragmentActivity activity = getActivity();
        CateAdapter cateAdapter = new CateAdapter(activity,(ArrayList<HomeBean.DataBean.CategoryListBean>) categoryList, getActivity());
        mRecCate.setAdapter(cateAdapter);

    }

    private void initTop(List<HomeBean.DataBean.TopicListBean> topicList) {
        mRecTop.setHasFixedSize(true);
        mRecTop.setNestedScrollingEnabled(false);
        TopAdapter topAdapter = new TopAdapter(getActivity(), (ArrayList<HomeBean.DataBean.TopicListBean>) topicList);
        mRecTop.setAdapter(topAdapter);
    }

    private void initHot(List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList) {
        HotAdapter hotAdapter = new HotAdapter(getActivity(), (ArrayList<HomeBean.DataBean.HotGoodsListBean>) hotGoodsList);
        mRecyHot.setAdapter(hotAdapter);
    }

    private void initNew(List<HomeBean.DataBean.NewGoodsListBean> hotGoodsList) {
        NewAdapter newAdapter = new NewAdapter(getActivity(), (ArrayList<HomeBean.DataBean.NewGoodsListBean>) hotGoodsList);
        mRecyNewgood.setAdapter(newAdapter);
    }

    private void initBrand(List<HomeBean.DataBean.BrandListBean> brandList) {
        BrandAdapter brandAdapter = new BrandAdapter(getActivity(), (ArrayList<HomeBean.DataBean.BrandListBean>) brandList);
        mRecyBrand.setAdapter(brandAdapter);
    }

    /**
     * 初始化banner
     *
     * @param list
     */
    private void initBanner(List<HomeBean.DataBean.BannerBean> list) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            strings.add(list.get(i).getImage_url());
            Log.e("tag",strings.get(i));
        }
        mBanner.setImages(strings).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load(path).into(imageView);
            }
        }).start();
    }

    /**
     * 初始化channel
     */
    private void initChannel(List<HomeBean.DataBean.ChannelBean> list) {
        if (mLayoutTab!=null){
            mLayoutTab.removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            for (int i = 0; i < list.size(); i++) {
                View channel = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel_item, null);
                mLayoutTab.addView(channel);
                ImageView img = channel.findViewById(R.id.img_channel);
                TextView textView = channel.findViewById(R.id.txt_channel);
                LinearLayout mLin = channel.findViewById(R.id.channel_item_lin);
                mLin.setLayoutParams(layoutParams);
                textView.setText(list.get(i).getName());
                Glide.with(getActivity()).load(list.get(i).getIcon_url()).into(img);

                int finalI = i;
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = list.get(finalI).getUrl();
                        String[] split = url.split("=");
                        String s = split[1];
                        Intent intent = new Intent(getActivity(), TiaoZhuanActivity.class);
                        intent.putExtra("id",s);
                        intent.putExtra("name",list.get(finalI).getName());
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
