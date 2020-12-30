package com.example.p8wangyi.interfaces;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {

    void addDisposible(Disposable disposable);

    void clear();

}
