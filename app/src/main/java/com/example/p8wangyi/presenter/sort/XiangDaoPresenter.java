package com.example.p8wangyi.presenter.sort;

import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;
import com.example.p8wangyi.interfaces.sort.IDao;
import com.example.p8wangyi.model.sort.XiangDaoBean;
import com.example.p8wangyi.model.sort.XiangDaoModel;

public class XiangDaoPresenter extends BasePresenter<IDao.View> implements IDao.Presenter {

    private IDao.Model im ;

    public XiangDaoPresenter() {
        this.im = new XiangDaoModel();
    }

    @Override
    public void OnGet(int id) {
        im.OnEnter(id, new Callback() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.OnEnter((XiangDaoBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
