package com.example.p8wangyi.model.sort;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.ISortNei;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class SortNeiModel extends BaseModel implements ISortNei.Model {
    @Override
    public void OnEnter(int id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getNei(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<SortNeiBean>(callback) {
                            @Override
                            public void onNext(SortNeiBean sortNeiBean) {
                                callback.success(sortNeiBean);
                            }
                        })
        );
    }
}
