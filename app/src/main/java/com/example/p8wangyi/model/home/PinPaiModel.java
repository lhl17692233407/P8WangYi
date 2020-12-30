package com.example.p8wangyi.model.home;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IPinPai;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class PinPaiModel  extends BaseModel implements IPinPai.Model {
    @Override
    public void OnEnter(Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getPinPai()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PinPaiBean>(callback) {
                    @Override
                    public void onNext(PinPaiBean pinPaiBean) {
                        callback.success(pinPaiBean);
                    }
                })
        );
    }
}
