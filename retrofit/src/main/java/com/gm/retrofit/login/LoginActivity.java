package com.gm.retrofit.login;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gm.retrofit.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
    }

}
