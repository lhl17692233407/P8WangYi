package com.example.p8wangyi.ui.topic;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;

public interface ITwo {
    interface View extends IBaseView {
        void getTwoReturn(TopBean twoBean);
        void getTwooReturn(TopBean2 twooBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getTwo();
        void getTwoo();
    }

    interface Model extends IBaseModel {
        void getTwo(Callback callback);
        void getTwoo(Callback callback);

    }
}
