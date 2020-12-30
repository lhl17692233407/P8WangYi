package com.example.p8wangyi.model.home;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;

public class GouMaiJs {
    private Context context;

    public GouMaiJs(Context context) {
        this.context = context;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        Intent intent = new Intent(context, GouMaiActivity.class);
        intent.putExtra("img", img);
        context.startActivity(intent);
    }
}