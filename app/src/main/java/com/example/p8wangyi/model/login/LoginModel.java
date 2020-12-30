package com.example.p8wangyi.model.login;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.login.Ilogin;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.net.HttpManager2;
import com.example.p8wangyi.utils.RxUtils;
import com.tencent.mm.opensdk.utils.ILog;

public class LoginModel extends BaseModel implements Ilogin.Model {
    @Override
    public void OnEnter(String un, String pw, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getLogin(un,pw)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }
                })
        );
    }
}
