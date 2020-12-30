package com.example.p8wangyi.presenter.Login;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.login.Ilogin;
import com.example.p8wangyi.model.login.LoginBean;
import com.example.p8wangyi.model.login.LoginModel;

public class LoginPresenter extends BasePresenter<Ilogin.View> implements Ilogin.Presenter{

    private Ilogin.Model im;

    public LoginPresenter() {
        this.im = new LoginModel();
    }

    @Override
    public void OnGet(String un, String pw) {
        im.OnEnter(un, pw, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.onEnter((LoginBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
