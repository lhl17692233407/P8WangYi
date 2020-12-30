package com.example.p8wangyi.interfaces.sort;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.sort.ShuTabBean;

public interface IShu {

    interface View extends IBaseView{
        void OnEnter(ShuTabBean shuTabBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet();
    }

    interface Model extends IBaseModel{
        void OnEnter(Callback callback);
    }
}
