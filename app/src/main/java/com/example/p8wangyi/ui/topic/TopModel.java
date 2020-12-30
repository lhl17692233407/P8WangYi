package com.example.p8wangyi.ui.topic;


import com.example.p8wangyi.api.ShopApi;
import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class TopModel extends BaseModel implements ITwo.Model {
    private ShopApi api;

    public TopModel() {
        api= HttpManager.getInstance().getShopApi();
    }

    @Override
    public void getTwo(Callback callback) {
        addDisposible(api.getTwo().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<TopBean>(callback) {
            @Override
            public void onNext(TopBean twoBean) {
                callback.success(twoBean);
            }
        }));
    }

    @Override
    public void getTwoo(Callback callback) {
        addDisposible(api.getTwoo().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<TopBean2>(callback) {
            @Override
            public void onNext(TopBean2 twooBean) {
                callback.success(twooBean);
            }
        }));
    }
}
