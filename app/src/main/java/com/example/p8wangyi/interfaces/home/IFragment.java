package com.example.p8wangyi.interfaces.home;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.home.VpBean;

import java.util.Map;

public interface IFragment {

    interface View extends IBaseView{
        void OnEnter(VpBean vpBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet(int id);
    }

    interface Model extends IBaseModel{
        void OnEnter(int id, Callback callback);
    }
}
