package com.gm.retrofit;

import android.support.v7.app.AppCompatActivity;

import com.gm.retrofit.initscreen.InitActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class Splash extends AppCompatActivity {

    @AfterViews
    public void init() {
        doInBackground();
    }

    @Background(delay = 3000)
    public void doInBackground() {
        InitActivity_.intent(Splash.this).start();
        finish();
    }
}
