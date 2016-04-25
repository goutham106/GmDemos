package com.gm.retrofit.customview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;


public class TypeFaceManager {
    private static TypeFaceManager ourInstance = new TypeFaceManager();

    private final HashMap<String, Typeface> mTypefaces = new HashMap<>();

    public static TypeFaceManager getInstance() {
        return ourInstance;
    }

    private TypeFaceManager() {
    }


    public void applyTypeface(TextView textView, Context context, AttributeSet attrs,int tyepedArrayId[], int fontNameId) {

        TypedArray a = context.obtainStyledAttributes(attrs,tyepedArrayId);
        String fontName = a.getString(fontNameId);
        if (fontName!=null) {
            Typeface tempTypeface = mTypefaces.get(fontName);
            if (tempTypeface == null) {
                tempTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/"+fontName);
                textView.setTypeface(tempTypeface);
                mTypefaces.put(fontName, tempTypeface);
            }else
            {
                textView.setTypeface(tempTypeface);
            }
        }
        a.recycle();
    }

    public void applyTypeface(EditText textView, Context context, AttributeSet attrs, int tyepedArrayId[], int fontNameId) {

        TypedArray a = context.obtainStyledAttributes(attrs,tyepedArrayId);
        String fontName = a.getString(fontNameId);
        if (fontName!=null) {
            Typeface tempTypeface = mTypefaces.get(fontName);
            if (tempTypeface == null) {
                tempTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/"+fontName);
                textView.setTypeface(tempTypeface);
                mTypefaces.put(fontName, tempTypeface);
            }else
            {
                textView.setTypeface(tempTypeface);
            }
        }
        a.recycle();
    }
}
