package com.gm.gmannotation;

import android.support.v7.app.AppCompatActivity;

import com.gm.gmannotation.activities.FirstActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

@Fullscreen
@EActivity(R.layout.activity_splash)
public class Splash extends AppCompatActivity {

    private final int DELAYSECONDS = 3000;

    @AfterViews
    public void init() {
        runSplash();
    }

    @Background(delay = DELAYSECONDS)
    public void runSplash() {
       // HomeActvity_.intent(Splash.this).start();
        FirstActivity_.intent(Splash.this).start();
        finish();
    }
}
