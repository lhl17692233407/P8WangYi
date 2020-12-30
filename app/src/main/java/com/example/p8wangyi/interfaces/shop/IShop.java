package com.example.p8wangyi.interfaces.shop;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.shop.DeleteCarBean;
import com.example.p8wangyi.model.shop.ShopBean;
import com.example.p8wangyi.model.shop.UpdateCarBean;

import java.util.Map;

public interface IShop {

    interface View extends IBaseView{
        void OnEnter(ShopBean shopBean);
        void OnEnter(UpdateCarBean updateCarBean);
        void OnEnter(DeleteCarBean deleteCarBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet();
        void OnGet(Map<String,String> map);
        void OnGet(String id);
    }

    interface Model extends IBaseModel{
        void OnEnter(Callback callback);
        void OnEnter(Callback callback,Map<String,String> map);
        void OnEnter(Callback callback,String id);
    }
}
