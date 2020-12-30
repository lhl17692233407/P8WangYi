package com.example.p8wangyi.ui.sort;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseFragment;
import com.example.p8wangyi.interfaces.sort.IShu;
import com.example.p8wangyi.model.sort.ShuTabBean;
import com.example.p8wangyi.presenter.sort.ShuPresenter;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;

public class SortFragment extends BaseFragment<ShuPresenter> implements IShu.View {
    private VerticalTabLayout mSortVtab;
    private ViewPager mSortVp;
    private List<ShuTabBean.DataBean.CategoryListBean> categoryList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected ShuPresenter createPrenter() {
        return new ShuPresenter();
    }

    @Override
    protected void initView(View view) {

        mSortVtab =view. findViewById(R.id.sort_vtab);
        mSortVp = view.findViewById(R.id.sort_vp);
    }

    @Override
    protected void initData() {
        presenter.OnGet();
    }

    @Override
    public void OnEnter(ShuTabBean shuTabBean) {
        categoryList = shuTabBean.getData().getCategoryList();
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<SortNeiFragment> fragments = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            Log.e("tag1",categoryList.size()+"");
            SortNeiFragment sortFragment = new SortNeiFragment();
            Bundle args = new Bundle();
            args.putInt("id",categoryList.get(i).getId());
            sortFragment.setArguments(args);
            fragments.add(sortFragment);
            strings.add(categoryList.get(i).getName());
        }

        SortVpAdapter sortVpAdapter = new SortVpAdapter(getChildFragmentManager(), fragments, strings);
        mSortVp.setAdapter(sortVpAdapter);
        mSortVtab.setupWithViewPager(mSortVp);
    }
}
