package com.example.p8wangyi.presenter.shop;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.shop.IShop;
import com.example.p8wangyi.model.shop.DeleteCarBean;
import com.example.p8wangyi.model.shop.ShopBean;
import com.example.p8wangyi.model.shop.ShopModel;
import com.example.p8wangyi.model.shop.UpdateCarBean;

import java.util.Map;

public class ShopPresenter extends BasePresenter<IShop.View> implements IShop.Presenter{

    private IShop.Model im;

    public ShopPresenter() {
        this.im = new ShopModel();
    }

    @Override
    public void OnGet() {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((ShopBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void OnGet(Map<String, String> map) {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                mView.OnEnter((UpdateCarBean) data);
            }

            @Override
            public void fail(String err) {

            }
        },map);
    }

    @Override
    public void OnGet(String id) {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                mView.OnEnter((DeleteCarBean) data);
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }
}
