package com.example.p8wangyi.model.home;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.home.IXiangQing;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

import java.util.concurrent.Callable;

public class NewXiangQingModel extends BaseModel implements IXiangQing.Model {
    @Override
    public void OnEnter(int id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getXiangQing(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<NewXiangQingBean>(callback) {
                    @Override
                    public void onNext(NewXiangQingBean newXiangQingBean) {
                        callback.success(newXiangQingBean);
                    }
                })
        );
    }

    @Override
    public void OnEnter(Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getXiangQingShang(1001000,1,1000)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<XiangQingShangBean>(callback) {
                    @Override
                    public void onNext(XiangQingShangBean xiangQingShangBean) {
                        callback.success(xiangQingShangBean);
                    }
                })
        );
    }
}
