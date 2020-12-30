package com.example.p8wangyi.presenter.home;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IXiangQing;
import com.example.p8wangyi.model.home.NewXiangQingBean;
import com.example.p8wangyi.model.home.NewXiangQingModel;
import com.example.p8wangyi.model.home.XiangQingShangBean;

public class NewXiangQingPresenter extends BasePresenter<IXiangQing.View> implements IXiangQing.Presenter {

    IXiangQing.Model im;

    public NewXiangQingPresenter() {
        this.im = new NewXiangQingModel();
    }

    @Override
    public void OnGet(int id) {
        im.OnEnter(id, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((NewXiangQingBean) data);
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
                mView.OnEnter((XiangQingShangBean) data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
