package com.example.p8wangyi.model.me;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.me.IMe;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.net.HttpManager2;
import com.example.p8wangyi.utils.RxUtils;

import java.util.Map;

public class MeModel extends BaseModel implements IMe.Model {

    @Override
    public void OnEnter(Map<String, String> map, Callback callback) {
        addDisposible(
                HttpManager2.getInstance().getShopApi().updateUserInfo(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserInfoBean>(callback) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {

                    }
                })
        );
    }
}
