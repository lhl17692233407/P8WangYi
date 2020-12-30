package com.example.p8wangyi.model.shop;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.shop.IShop;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.net.HttpManager2;
import com.example.p8wangyi.utils.RxUtils;

import java.util.Map;

public class ShopModel extends BaseModel implements IShop.Model {
    @Override
    public void OnEnter(Callback callback) {
        addDisposible(
                HttpManager2.getInstance().getShopApi().getShop()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ShopBean>(callback) {
                    @Override
                    public void onNext(ShopBean shopBean) {
                        callback.success(shopBean);
                    }
                })
        );
    }

    @Override
    public void OnEnter(Callback callback, Map<String, String> map) {
        addDisposible(
                HttpManager2.getInstance().getShopApi().updateCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                })
        );
    }

    @Override
    public void OnEnter(Callback callback, String id) {
        addDisposible(
                HttpManager2.getInstance().getShopApi().deleteCar(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                })
        );
    }
}
