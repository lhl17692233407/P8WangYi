package com.example.p8wangyi.presenter.sort;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.IShu;
import com.example.p8wangyi.model.sort.ShuModel;
import com.example.p8wangyi.model.sort.ShuTabBean;

public class ShuPresenter extends BasePresenter<IShu.View> implements IShu.Presenter {

    private IShu.Model im;

    public ShuPresenter() {
        this.im = new ShuModel();
    }
    @Override
    public void OnGet() {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((ShuTabBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
