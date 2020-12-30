package com.example.p8wangyi.model.home;


import android.util.Log;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IHome;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class HomeModel extends BaseModel implements IHome.Model {


    @Override
    public void getHome(Callback callback) {
        Log.e("tag", "444444444444444444444");
        addDisposible(
                HttpManager.getInstance().getShopApi().getHome()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<HomeBean>(callback) {
                            @Override
                            public void onNext(HomeBean homeBean) {
                                Log.e("tag", homeBean.getErrmsg() + "bbbbbbbbbbbbbb");
                                callback.success(homeBean);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                            }
                        }));
    }
}
