package com.example.p8wangyi.ui.topic;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.p8wangyi.R;
import com.example.p8wangyi.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Top2Activity extends BaseActivity<ITwo.Presenter> implements ITwo.View {
    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;
    @BindView(R.id.bnt_one1)
    Button bntOne;
    @BindView(R.id.bnt_two1)
    Button bntTwo;

    private List<TopBean2.DataBeanX.DataBean> list;
    private Top2Adapter twooAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_two;
    }

    @Override
    protected ITwo.Presenter createPrenter() {
        return new TopPresenter(this);
    }


    @Override
    protected void initView() {

        bntOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bntOne.setTextColor(0x151515);
                finish();
            }
        });
        bntTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bntTwo.setTextColor(0xCFCFCC);
            }
        });

        list = new ArrayList<>();

        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        twooAdapter = new Top2Adapter(this, list);
        recyclerMain.setAdapter(twooAdapter);
    }

    @Override
    protected void initData() {
        presenter.getTwoo();
    }

    @Override
    public void getTwoReturn(TopBean twoBean) {

    }

    @Override
    public void getTwooReturn(TopBean2 twooBean) {
        list.clear();
        list.addAll(twooBean.getData().getData());
        twooAdapter.notifyDataSetChanged();
    }
}
