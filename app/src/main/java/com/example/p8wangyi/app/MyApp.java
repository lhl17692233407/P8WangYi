package com.example.p8wangyi.app;

import android.app.Application;

import com.example.p8wangyi.utils.SpUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApp extends Application {
    public static MyApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        SpUtils.getInstance().setValue("image",true);

        initRealm();
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().name("User.db").build();
        Realm.setDefaultConfiguration(config);
    }
}
