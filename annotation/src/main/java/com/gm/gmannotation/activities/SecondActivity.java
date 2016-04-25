package com.gm.gmannotation.activities;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

import com.gm.gmannotation.R;
import com.gm.gmannotation.customview.CustomTextView;
import com.gm.gmannotation.fragments.MainActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_second)
public class SecondActivity extends AppCompatActivity {

    @Extra
    String extravalue;
    @ViewById
    CustomTextView txtValues;
    @ViewById
    Button btnGetValue, btnGotoFragment;

    @Click
    public void btnGetValue() {
        if (!TextUtils.isEmpty(extravalue))
            txtValues.setText(extravalue);
    }

    @Click
    public void btnGotoFragment() {
        MainActivity_.intent(SecondActivity.this).start();
    }

}
