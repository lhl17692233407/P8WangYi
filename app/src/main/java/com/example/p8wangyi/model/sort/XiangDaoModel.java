package com.example.p8wangyi.model.sort;

import com.example.p8wangyi.base.BaseModel;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.IDao;
import com.example.p8wangyi.net.CommonSubscriber;
import com.example.p8wangyi.net.HttpManager;
import com.example.p8wangyi.utils.RxUtils;

public class XiangDaoModel extends BaseModel implements IDao.Model {
    @Override
    public void OnEnter(int id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getDao(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<XiangDaoBean>(callback) {
                    @Override
                    public void onNext(XiangDaoBean xiangDaoBean) {
                        callback.success(xiangDaoBean);
                    }
                })
        );
    }
}
