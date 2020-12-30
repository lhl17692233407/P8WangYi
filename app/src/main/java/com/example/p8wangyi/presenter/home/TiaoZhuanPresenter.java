package com.example.p8wangyi.presenter.home;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IHome;
import com.example.p8wangyi.interfaces.home.ITiaoZhuan;
import com.example.p8wangyi.model.home.TiaoZhanModel;
import com.example.p8wangyi.model.home.TiaoZhuanBean;

public class TiaoZhuanPresenter extends BasePresenter<ITiaoZhuan.View> implements ITiaoZhuan.Presenter {
        ITiaoZhuan.Model im;

    public TiaoZhuanPresenter() {
        this.im = new TiaoZhanModel();
    }

    @Override
    public void OnGet(int id) {
        im.OnEnter(new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.onEnter((TiaoZhuanBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        },id);
    }
}
