package com.gm.gmannotation.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gowtham on 4/20/2016.
 */
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}
