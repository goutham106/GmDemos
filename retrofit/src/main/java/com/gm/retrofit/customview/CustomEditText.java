package com.gm.retrofit.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.gm.retrofit.R;


/**
 * Created by gowtham on 18/04/16.
 */
public class CustomEditText extends  EditText{

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEditText(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypeFaceManager.getInstance().applyTypeface(this, getContext(), attrs, R.styleable.CustomEditText, R.styleable.CustomEditText_fontNames);
        }
    }
}
