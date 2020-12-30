package com.example.p8wangyi.presenter.sort;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.ISortNei;
import com.example.p8wangyi.model.sort.SortNeiBean;
import com.example.p8wangyi.model.sort.SortNeiModel;

public class SortNeiPresenter extends BasePresenter<ISortNei.View> implements ISortNei.Presenter{

    private ISortNei.Model im;

    public SortNeiPresenter() {
        this.im = new SortNeiModel();
    }

    @Override
    public void OnGet(int id) {
        im.OnEnter(id, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((SortNeiBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
