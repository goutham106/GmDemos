package com.gm.gmannotation.activities.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gm.gmannotation.R;
import com.gm.gmannotation.activities.model.TestModel;
import com.gm.gmannotation.customview.CustomTextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Gowtham on 4/20/2016.
 */
@EViewGroup(R.layout.adapter_test)
public class TestViewHolder extends LinearLayout {

    @ViewById
    CustomTextView userNameTextView, userphoneNumberTextView;
    @ViewById
    LinearLayout adapterLinearBase;

    Context context;

    public TestViewHolder(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(final TestModel testModel) {
        userNameTextView.setText(testModel.getName());
        userphoneNumberTextView.setText(testModel.getMobileNumber());

        adapterLinearBase.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, testModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
