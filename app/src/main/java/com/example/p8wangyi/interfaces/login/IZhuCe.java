package com.example.p8wangyi.interfaces.login;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.login.ZhuCeBean;

public interface IZhuCe {

    interface View extends IBaseView{
        void OnEnter(ZhuCeBean zhuCeBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet(String usname,String password);
    }

    interface Model extends IBaseModel{
        void OnEnter(String usname,String password,Callback callback);
    }
}
