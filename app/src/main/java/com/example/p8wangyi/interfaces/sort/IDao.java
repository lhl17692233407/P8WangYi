package com.example.p8wangyi.interfaces.sort;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.sort.XiangDaoBean;

public interface IDao {

    interface View extends IBaseView{
        void OnEnter(XiangDaoBean xiangDaoBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void OnGet(int id);
    }

    interface Model extends IBaseModel{
        void OnEnter(int id, Callback callback);
    }
}
