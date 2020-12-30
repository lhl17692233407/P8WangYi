package com.example.p8wangyi.presenter.me;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.me.IMe;
import com.example.p8wangyi.model.me.MeModel;
import com.example.p8wangyi.model.me.UserInfoBean;

import java.util.Map;

public class MePresenter extends BasePresenter<IMe.View> implements IMe.Presenter{

    private IMe.Model im;

    public MePresenter() {
        this.im = new MeModel();
    }

    @Override
    public void OnGet(Map<String, String> map) {
        im.OnEnter(map, new Callback() {
            @Override
            public void success(Object data) {
                mView.OnEnter((UserInfoBean) data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
