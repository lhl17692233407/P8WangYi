package com.example.p8wangyi.interfaces.login;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.login.LoginBean;

public interface Ilogin {
    interface View extends IBaseView{
        void onEnter(LoginBean loginBean);
    }
    interface Presenter extends IBasePresenter<View>{
        void OnGet(String un,String pw);
    }

    interface Model extends IBaseModel{
        void OnEnter(String un, String pw, Callback callback);
    }
}
