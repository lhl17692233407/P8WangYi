package com.example.p8wangyi.presenter.home;


import android.util.Log;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IHome;
import com.example.p8wangyi.model.home.HomeBean;
import com.example.p8wangyi.model.home.HomeModel;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.Model model;
    public HomePresenter(){
        model = new HomeModel();
    }

    @Override
    public void getHome() {
        Log.e("tag","33333333333333333333");
        model.getHome(new Callback<HomeBean>() {
            @Override
            public void success(HomeBean data) {
                Log.e("tag","555");
                    Log.e("tag","5555555555555");
                    if (mView!=null){
                        mView.getHomeReturn(data);
                    }

            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
