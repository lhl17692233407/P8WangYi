package com.example.p8wangyi.interfaces;

public interface Callback<T> {

    void success(T data);

    void fail(String err);

}
