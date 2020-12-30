package com.example.p8wangyi.presenter.Login;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.login.IZhuCe;
import com.example.p8wangyi.model.login.ZhuCeBean;
import com.example.p8wangyi.model.login.ZhuCeModel;

public class ZhuCePresenter extends BasePresenter<IZhuCe.View> implements IZhuCe.Presenter {

    private IZhuCe.Model im;

    public ZhuCePresenter() {
        this.im = new ZhuCeModel();
    }

    @Override
    public void OnGet(String usname, String password) {
        im.OnEnter(usname, password, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((ZhuCeBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
