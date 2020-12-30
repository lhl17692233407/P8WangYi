package com.example.p8wangyi.model.sort;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.IDaoNei;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class IDaoNeiModel extends BaseModel implements IDaoNei.Model {
    @Override
    public void OnModel(int id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getDaoNei(id,1,100)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<IDaoNeiBean>(callback) {
                    @Override
                    public void onNext(IDaoNeiBean iDaoNeiBean) {
                        callback.success(iDaoNeiBean);
                    }
                })
        );
    }
}
