package com.example.p8wangyi.interfaces.home;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.home.TuPianBean;
import com.example.p8wangyi.model.home.ZhouYiBean;

import java.util.HashMap;
import java.util.Map;

public interface IZhouYi {

    interface View extends IBaseView{
        void OnEnter(ZhouYiBean zhouYiBean);
        void OnEnter(TuPianBean tupian);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet(HashMap<String,String> map);
        void OnGet();
    }

    interface Model extends IBaseModel{
        void OnEnter(HashMap<String, String> map, Callback callback);
        void OnEnter(Callback callback);
    }
}
