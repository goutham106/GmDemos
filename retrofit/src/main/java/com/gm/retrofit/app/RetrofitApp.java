package com.gm.retrofit.app;

import android.app.Application;

import java.io.File;

/**
 * Created by Gowtham on 4/21/2016.
 */
public class RetrofitApp extends Application {

    public static RetrofitApp retrofitApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitApp = this;
    }

    public static RetrofitApp getInstance() {
        return retrofitApp;
    }

    public static File getCacheDirectory() {
        return retrofitApp.getCacheDir();
    }

}
