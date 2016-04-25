package com.gm.gmannotation.activities;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

import com.gm.gmannotation.R;
import com.gm.gmannotation.customview.CustomEditText;
import com.gm.gmannotation.customview.CustomTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_first)
public class FirstActivity extends AppCompatActivity {

    @ViewById
    CustomEditText edtSetBundle;

    @ViewById
    CustomTextView txtTitle;

    @ViewById
    Button btnSend;

    @StringRes
    String first_screen;

    @AfterViews
    public void init() {

    }

    @Click
    public void btnSend() {
        //SecondActivity_.intent(FirstActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();

        SecondActivity_.intent(FirstActivity.this)
                .extravalue(edtSetBundle.getText().toString())
                .start();
    }

    @TextChange({R.id.edtSetBundle})
    public void onEditTextChange() {
        txtTitle.setText(edtSetBundle.getText().toString());
        if (TextUtils.isEmpty(edtSetBundle.getText().toString()))
            txtTitle.setText(first_screen);
    }

    //    @AfterTextChange
//    public void edtSetBundle(){
//        txtTitle.setText("Super");
//    }
    @Click
    public void btnSendThirdActivity() {
        ThirdActivity_.intent(FirstActivity.this).start();
    }
}
