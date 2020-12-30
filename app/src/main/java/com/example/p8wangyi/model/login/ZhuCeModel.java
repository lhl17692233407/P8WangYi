package com.example.p8wangyi.model.login;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IZhouYi;
import com.example.p8wangyi.interfaces.login.IZhuCe;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.net.HttpManager2;
import com.example.p8wangyi.utils.RxUtils;

public class ZhuCeModel extends BaseModel implements IZhuCe.Model {
    @Override
    public void OnEnter(String usname, String password, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getZhuCe(usname,password)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ZhuCeBean>(callback) {
                    @Override
                    public void onNext(ZhuCeBean zhuCeBean) {
                        callback.success(zhuCeBean);
                    }
                })
        );
    }
}
