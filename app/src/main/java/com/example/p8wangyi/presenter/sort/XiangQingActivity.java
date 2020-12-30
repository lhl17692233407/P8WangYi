package com.example.p8wangyi.presenter.sort;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;
import com.example.p8wangyi.interfaces.sort.IDao;
import com.example.p8wangyi.model.sort.XiangDaoBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class XiangQingActivity extends BaseActivity<IDao.Presenter> implements IDao.View {
    private TabLayout mXiangqingDaoTab;
    private ViewPager mXiangqingDaoVp;
    private List<XiangDaoBean.DataBean.BrotherCategoryBean> brotherCategory;
    private ArrayList<XiangNeiFragment> fragments;
    private ArrayList<String> strings;

    @Override
    protected int getLayout() {
        return R.layout.activity_xiang_qing;
    }

    @Override
    protected IDao.Presenter createPrenter() {
        return new XiangDaoPresenter();
    }

    @Override
    protected void initView() {

        mXiangqingDaoTab = findViewById(R.id.xiangqing_dao_tab);
        mXiangqingDaoVp = findViewById(R.id.xiangqing_dao_vp);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        presenter.OnGet(id);
    }

    @Override
    public void OnEnter(XiangDaoBean xiangDaoBean) {
        brotherCategory = xiangDaoBean.getData().getBrotherCategory();
        fragments = new ArrayList<>();
        strings = new ArrayList<>();
        for (int i = 0; i < brotherCategory.size(); i++) {
            XiangNeiFragment xiangNeiFragment = new XiangNeiFragment();
            Bundle args = new Bundle();
            args.putInt("id",brotherCategory.get(i).getId());
            args.putString("name",brotherCategory.get(i).getName());
            args.putString("name2",brotherCategory.get(i).getFront_name());
            xiangNeiFragment.setArguments(args);
            fragments.add(xiangNeiFragment);
            strings.add(brotherCategory.get(i).getName());
        }
        Adapter adapter = new Adapter(getSupportFragmentManager());
        mXiangqingDaoVp.setAdapter(adapter);
        mXiangqingDaoTab.setupWithViewPager(mXiangqingDaoVp);
    }

    class  Adapter extends FragmentStatePagerAdapter{

        public Adapter(@NonNull FragmentManager fm) {
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
            return strings.get(position);
        }
    }
}