package com.example.p8wangyi.presenter.home;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.home.IFragment;
import com.example.p8wangyi.model.home.FragmentModel;
import com.example.p8wangyi.model.home.VpBean;

import java.util.Map;

public class FragmentPresenter extends BasePresenter<IFragment.View> implements IFragment.Presenter {
    IFragment.Model im;

    public FragmentPresenter() {
        this.im = new FragmentModel();
    }

    @Override
    public void OnGet(int id) {
        im.OnEnter(id, new Callback() {
            @Override
            public void success(Object data) {
                if (data!=null){
                    if (mView!=null){
                        mView.OnEnter((VpBean) data);
                    }
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
