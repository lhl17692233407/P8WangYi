package com.example.p8wangyi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.home.ITiaoZhuan;
import com.example.p8wangyi.model.home.TiaoZhuanBean;
import com.example.p8wangyi.presenter.home.TiaoZhuanPresenter;
import com.example.p8wangyi.ui.home.DaoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TiaoZhuanActivity extends BaseActivity<TiaoZhuanPresenter> implements ITiaoZhuan.View {

    private TabLayout mTiaozhuanTab;
    private ViewPager mTiaozhuanVp;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> name;
    private String id;
    private int i;
    private String name1;

    @Override
    protected int getLayout() {
        return R.layout.activity_tiao_zhuan;
    }

    @Override
    protected TiaoZhuanPresenter createPrenter() {
        return new TiaoZhuanPresenter();
    }

    @Override
    protected void initView() {
        mTiaozhuanTab = findViewById(R.id.tiaozhuan_tab);
        mTiaozhuanVp = findViewById(R.id.tiaozhuan_vp);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name1 = intent.getStringExtra("name");
        presenter.OnGet(1005000);
        i = Integer.parseInt(id);


    }

    @Override
    public void onEnter(TiaoZhuanBean tiaoZhuanBean) {
        List<TiaoZhuanBean.DataBean.BrotherCategoryBean> categoryList = tiaoZhuanBean.getData().getBrotherCategory();
        fragments = new ArrayList<>();
        name = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            DaoFragment daoFragment = new DaoFragment();
            Bundle args = new Bundle();
            args.putInt("id", categoryList.get(i).getId());
            args.putString("name",categoryList.get(i).getName());
            args.putString("name1",categoryList.get(i).getFront_name());
            daoFragment.setArguments(args);
            fragments.add(daoFragment);
            name.add(categoryList.get(i).getName());
        }
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mTiaozhuanVp.setAdapter(vpAdapter);
        mTiaozhuanTab.setupWithViewPager(mTiaozhuanVp);

        for (int j = 0; j <categoryList.size();j++) {
            if (name1.equals(categoryList.get(j).getName())){
                mTiaozhuanTab.getTabAt(j).select();
            }
        }

    }
    class VpAdapter extends FragmentStatePagerAdapter{

        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return name.get(position);
        }
    }

}