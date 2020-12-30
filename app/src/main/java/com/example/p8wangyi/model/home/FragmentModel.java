package com.example.p8wangyi.model.home;

import android.util.Log;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IFragment;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

import java.util.Map;

public class FragmentModel extends BaseModel implements IFragment.Model {

    @Override
    public void OnEnter(int id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getData1(id,1,"200x")
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<VpBean>(callback) {
                            @Override
                            public void onNext(VpBean vpBean) {
                                callback.success(vpBean);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                Log.e("tag",t.toString());
                            }
                        })
        );
    }
}
