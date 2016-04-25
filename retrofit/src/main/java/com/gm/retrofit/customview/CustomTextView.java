package com.gm.retrofit.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gm.retrofit.R;

public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypeFaceManager.getInstance().applyTypeface(this, getContext(), attrs, R.styleable.CustomTextView, R.styleable.CustomTextView_fontName);
        }
    }

}
