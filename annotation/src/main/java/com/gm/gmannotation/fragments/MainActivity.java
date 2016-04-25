package com.gm.gmannotation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gm.gmannotation.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
        setFragment(new FirstFragment_(), "FirstFragment", null);
    }


    public void setFragment(Fragment frag, String TAG, Bundle args) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (args != null)
            frag.setArguments(args);
        ft.replace(R.id.fragmentContainer, frag, TAG);
        ft.commit();

        if (frag instanceof SecondFragment_) {
            toolbar.setTitle("Hello!!!");
           // getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @OptionsItem(android.R.id.home)
    public void onBackPress() {
        onBackPressed();
    }

}
