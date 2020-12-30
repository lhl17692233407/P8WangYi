package com.example.p8wangyi.interfaces.home;


import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.home.HomeBean;

public interface IHome {

    interface View extends IBaseView {
        void getHomeReturn(HomeBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHome();
    }

    interface Model extends IBaseModel {
        void getHome(Callback callback);
    }

}
