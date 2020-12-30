package com.example.p8wangyi.ui.sort;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SortVpAdapter extends FragmentPagerAdapter {

    private ArrayList<SortNeiFragment> list;
    private ArrayList<String> arr;

    public SortVpAdapter(@NonNull FragmentManager fm, ArrayList<SortNeiFragment> list, ArrayList<String> arr) {
        super(fm);
        this.list = list;
        this.arr = arr;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arr.get(position);
    }
}
