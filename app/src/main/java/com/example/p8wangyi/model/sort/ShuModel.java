package com.example.p8wangyi.model.sort;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.IShu;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class ShuModel extends BaseModel implements IShu.Model {
    @Override
    public void OnEnter(Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getTab(1005000)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ShuTabBean>(callback) {
                    @Override
                    public void onNext(ShuTabBean shuTabBean) {
                        callback.success(shuTabBean);
                    }
                })
        );
    }
}
