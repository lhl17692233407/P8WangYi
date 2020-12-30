package com.example.p8wangyi.ui.topic;


import com.example.p8wangyi.base.BasePresenter;
import com.example.p8wangyi.interfaces.Callback;

public class TopPresenter extends BasePresenter<ITwo .View> implements ITwo.Presenter{
    ITwo.Model model;
    ITwo.View view;

    public TopPresenter(ITwo.View view) {
        this.view = view;
        model=new TopModel();
    }


    @Override
    public void getTwo() {
        if (view!=null){
            model.getTwo(new Callback() {
                @Override
                public void success(Object data) {
                    view.getTwoReturn((TopBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }

    @Override
    public void getTwoo() {
        if (view!=null){
            model.getTwoo(new Callback() {
                @Override
                public void success(Object data) {
                    view.getTwooReturn((TopBean2) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }
}
