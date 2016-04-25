package com.gm.retrofit.common;

import android.support.v7.app.AppCompatActivity;

import com.gm.retrofit.webservice.interfaces.DataSelectionCallback;

/**
 * Created by Gowtham on 4/22/2016.
 */
public class AppCompatParent extends AppCompatActivity implements DataSelectionCallback{

    @Override
    public void dataSelection(int whichView, int selectedPos) {

    }

    @Override
    public void alertPositiveBtn(int whichView, int statusCode) {

    }

    @Override
    public void alertNegativeBtn(int whichView, int statusCode) {

    }

    @Override
    public void onReConnect() {

    }

    @Override
    public void onResetWebservice(Boolean isSuccess) {

    }
}
