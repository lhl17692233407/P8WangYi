package com.example.p8wangyi.presenter.home;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IPinPai;
import com.example.p8wangyi.model.home.PinPaiBean;
import com.example.p8wangyi.model.home.PinPaiModel;


public class PinPaiPresenter extends BasePresenter<IPinPai.View> implements IPinPai.Presenter {
    private IPinPai.Model im;

    public PinPaiPresenter() {
        im = new PinPaiModel();
    }

    @Override
    public void OnGet() {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((PinPaiBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
