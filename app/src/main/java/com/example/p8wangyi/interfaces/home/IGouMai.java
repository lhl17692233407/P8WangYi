package com.example.p8wangyi.interfaces.home;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.home.AddShopCarBean;
import com.example.p8wangyi.model.home.GouMaiBean;

import java.util.Map;

public interface IGouMai {

    interface View extends IBaseView{
        void OnEnter(GouMaiBean gouMaiBean);
        void OnEnter(AddShopCarBean addShopCarBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet(int id);
        void OnGet(Map<String,String > map);
    }

    interface Model extends IBaseModel{
        void OnEnter(Callback callback,int id);
        void OnEnter(Map<String ,String > map,Callback callback);
    }
}
