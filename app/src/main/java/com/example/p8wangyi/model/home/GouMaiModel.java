package com.example.p8wangyi.model.home;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IGouMai;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.net.HttpManager2;
import com.example.p8wangyi.utils.RxUtils;

import java.util.Map;

public class GouMaiModel extends BaseModel implements IGouMai.Model {
    @Override
    public void OnEnter(Callback callback, int id) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getGouMai(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GouMaiBean>(callback) {
                    @Override
                    public void onNext(GouMaiBean gouMaiBean) {
                        callback.success(gouMaiBean);
                    }
                })
        );
    }

    @Override
    public void OnEnter(Map<String, String> map, Callback callback) {
        addDisposible(
                HttpManager2.getInstance().getShopApi().addShopCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddShopCarBean>(callback) {
                    @Override
                    public void onNext(AddShopCarBean addShopCarBean) {
                        callback.success(addShopCarBean);
                    }
                })
        );
    }

}
