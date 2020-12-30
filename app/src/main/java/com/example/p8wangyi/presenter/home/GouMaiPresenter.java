package com.example.p8wangyi.presenter.home;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.home.IGouMai;
import com.example.p8wangyi.model.home.AddShopCarBean;
import com.example.p8wangyi.model.home.GouMaiBean;
import com.example.p8wangyi.model.home.GouMaiModel;

import java.util.Map;

public class GouMaiPresenter extends BasePresenter<IGouMai.View> implements IGouMai.Presenter  {

    IGouMai.Model im;

    public GouMaiPresenter() {
        this.im = new GouMaiModel();
    }

    @Override
    public void OnGet(int id) {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((GouMaiBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }

    @Override
    public void OnGet(Map<String, String> map) {
        im.OnEnter(map, new Callback() {
            @Override
            public void success(Object data) {
                mView.OnEnter((AddShopCarBean) data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
