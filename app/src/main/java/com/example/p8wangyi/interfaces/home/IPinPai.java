package com.example.p8wangyi.interfaces.home;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.home.PinPaiBean;

public interface IPinPai {

    interface View extends IBaseView{
        void OnEnter(PinPaiBean pinPaiBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet();
    }

    interface Model extends IBaseModel{
        void OnEnter(Callback callback);
    }
}
