package com.example.p8wangyi.interfaces.sort;

import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.IBaseModel;
import com.example.p8wangyi.interfaces.IBasePresenter;
import com.example.p8wangyi.interfaces.IBaseView;
import com.example.p8wangyi.model.sort.IDaoNeiBean;

public interface IDaoNei {

    interface View extends IBaseView{
        void OnEnter(IDaoNeiBean iDaoNeiBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void OnGet(int id);
    }

    interface Model extends IBaseModel{
        void OnModel(int id, Callback callback);
    }
}
