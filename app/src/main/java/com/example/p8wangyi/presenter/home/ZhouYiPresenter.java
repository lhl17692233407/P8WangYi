package com.example.p8wangyi.presenter.home;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IZhouYi;
import com.example.p8wangyi.model.home.TuPianBean;
import com.example.p8wangyi.model.home.ZhouYiBean;
import com.example.p8wangyi.model.home.ZhouYiModel;

import java.util.HashMap;
import java.util.Map;

public class ZhouYiPresenter extends BasePresenter<IZhouYi.View> implements IZhouYi.Presenter {

    private IZhouYi.Model im;

    public ZhouYiPresenter() {
        this.im = new ZhouYiModel();
    }

    @Override
    public void OnGet(HashMap<String, String> map) {
        im.OnEnter(map, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((ZhouYiBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void OnGet() {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((TuPianBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
