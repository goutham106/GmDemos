package com.gm.gmannotation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;

import com.gm.gmannotation.R;
import com.gm.gmannotation.customview.CustomEditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_first)
public class FirstFragment extends Fragment {

    @ViewById
    CustomEditText edtSetBundle;
    @ViewById
    Button btnGotoSecondFragment;

    @Click
    public void btnGotoSecondFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("extraValue", edtSetBundle.getText().toString());
        ((MainActivity) getActivity()).setFragment(new SecondFragment_(), "SecondFragment", bundle);
    }

}
