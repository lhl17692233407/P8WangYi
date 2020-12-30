package com.example.p8wangyi.interfaces.me;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.me.UserInfoBean;

import java.util.Map;

public interface IMe {

    interface View extends IBaseView{
        void OnEnter(UserInfoBean userInfoBean);
    }
    interface Presenter extends IBasePresenter<View>{
        void OnGet(Map<String,String> map);
    }
    interface Model extends IBaseModel{
        void OnEnter(Map<String,String> map, Callback callback);
    }
}
