package com.gm.gmannotation.fragments;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.gm.gmannotation.R;
import com.gm.gmannotation.customview.CustomTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_second)
public class SecondFragment extends Fragment {

    @FragmentArg
    String extraValue;

    @ViewById
    CustomTextView txtValues;

    @AfterViews
    public void init(){
        if(!TextUtils.isEmpty(extraValue))
            txtValues.setText(extraValue);


    }

}
