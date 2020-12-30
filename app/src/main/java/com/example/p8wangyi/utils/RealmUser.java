package com.example.p8wangyi.utils;

import io.realm.RealmObject;

public class RealmUser extends RealmObject {
    private String name;
    private String img;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}