package com.gm.retrofit.initscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gm.retrofit.R;
import com.gm.retrofit.common.Enum;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_init)
public class InitActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;


    @AfterViews
    public void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        setFragment(new InitFragment_(),Enum.LoginProcess.INIT.name());
    }

    public void setFragment(Fragment frag, String TAG) {
        Bundle args = new Bundle();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        frag.setArguments(args);
        ft.replace(R.id.initContainer, frag, TAG);
        if (!TAG.equals(Enum.LoginProcess.INIT.name()))
            ft.addToBackStack(null);
        ft.commit();

    }

}
