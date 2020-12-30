package com.example.p8wangyi.ui.topic;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TopFragment extends BaseFragment<ITwo.Presenter> implements ITwo.View {



    private TopAdapter twoAdapter;
    private List<TopBean.DataBeanX.DataBean> list;
    private RecyclerView recyclerMain;

    @Override
    protected int getLayout() {
        return R.layout.fragment_twoo;
    }

    @Override
    protected ITwo.Presenter createPrenter() {
        return new TopPresenter(this);
    }

    @Override
    protected void initView(View view) {

        Button bntOne = view.findViewById(R.id.bnt_one);
        Button bntTwo = view.findViewById(R.id.bnt_two);
        recyclerMain = view.findViewById(R.id.recycler_main1);
        bntOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bntOne.setTextColor(0x151515);
            }
        });
        bntTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bntTwo.setTextColor(0xCFCFCC);
                Intent intent = new Intent(getContext(), Top2Activity.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();

        recyclerMain.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void initData() {
        presenter.getTwo();
    }


    @Override
    public void getTwoReturn(TopBean twoBean) {

        twoAdapter = new TopAdapter(getContext(), twoBean.getData().getData());
        recyclerMain.setAdapter(twoAdapter);
    }

    @Override
    public void getTwooReturn(TopBean2 twooBean) {

    }
}