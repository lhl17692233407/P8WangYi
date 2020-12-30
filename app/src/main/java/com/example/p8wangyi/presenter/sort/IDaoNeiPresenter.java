package com.example.p8wangyi.presenter.sort;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.IDaoNei;
import com.example.p8wangyi.model.sort.IDaoNeiBean;
import com.example.p8wangyi.model.sort.IDaoNeiModel;

public class IDaoNeiPresenter extends BasePresenter<IDaoNei.View> implements IDaoNei.Presenter {

    private IDaoNei.Model im;

    public IDaoNeiPresenter() {
        this.im = new IDaoNeiModel();
    }

    @Override
    public void OnGet(int id) {
        im.OnModel(id, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((IDaoNeiBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
