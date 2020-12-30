package com.example.p8wangyi.interfaces.home;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.home.NewXiangQingBean;
import com.example.p8wangyi.model.home.XiangQingShangBean;


public interface IXiangQing {

    interface View extends IBaseView{
        void OnEnter(NewXiangQingBean data);
        void OnEnter(XiangQingShangBean data2);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet(int id);
        void OnGet();
    }

    interface Model extends IBaseModel{
        void OnEnter(int id, Callback callback);
        void OnEnter(Callback callback);
    }
}
