package com.example.p8wangyi.model.home;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IZhouYi;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

import java.util.HashMap;

public class ZhouYiModel extends BaseModel implements IZhouYi.Model {
    @Override
    public void OnEnter(HashMap<String, String> map, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getZhouyi(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ZhouYiBean>(callback) {
                    @Override
                    public void onNext(ZhouYiBean zhouYiBean) {
                        callback.success(zhouYiBean);
                    }
                })
        );
    }

    @Override
    public void OnEnter(Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getTuPian()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TuPianBean>(callback) {
                    @Override
                    public void onNext(TuPianBean tuPianBean) {
                        callback.success(tuPianBean);
                    }
                })
        );
    }
}
