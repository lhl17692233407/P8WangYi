package com.example.p8wangyi.interfaces.home;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.home.TiaoZhuanBean;

public interface ITiaoZhuan {
    interface View extends IBaseView {
        void onEnter(TiaoZhuanBean tiaoZhuanBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void OnGet(int id);
    }

    interface Model extends IBaseModel{
        void OnEnter(Callback callback,int id);
    }
}
