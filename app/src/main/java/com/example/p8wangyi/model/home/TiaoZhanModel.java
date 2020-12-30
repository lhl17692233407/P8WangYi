package com.example.p8wangyi.model.home;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.ITiaoZhuan;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class  TiaoZhanModel extends BaseModel implements ITiaoZhuan.Model {
    @Override
    public void OnEnter(Callback callback, int id) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TiaoZhuanBean>(callback) {
                    @Override
                    public void onNext(TiaoZhuanBean tiaoZhuanBean) {
                        callback.success(tiaoZhuanBean);
                    }
                })
        );
    }
}
