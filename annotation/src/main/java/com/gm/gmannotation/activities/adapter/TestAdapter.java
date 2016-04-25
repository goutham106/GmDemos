package com.gm.gmannotation.activities.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.gm.gmannotation.activities.model.TestModel;
import com.gm.gmannotation.activities.viewholder.TestViewHolder;
import com.gm.gmannotation.activities.viewholder.TestViewHolder_;
import com.gm.gmannotation.util.RecyclerViewAdapterBase;
import com.gm.gmannotation.util.ViewWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham on 4/20/2016.
 */
public class TestAdapter extends RecyclerViewAdapterBase<TestModel, TestViewHolder> {

    Context context;
    List<TestModel> model = new ArrayList<>();

    public TestAdapter(Context context, List<TestModel> testModelList) {
        this.context = context;
        this.model = testModelList;
    }


    @Override
    protected TestViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        return TestViewHolder_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<TestViewHolder> holder, int position) {
        TestViewHolder viewHolder = holder.getView();
        TestModel testModel = this.model.get(position);
        viewHolder.bind(testModel);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public void clear() {
        model.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<TestModel> list) {
        model.addAll(list);
        notifyDataSetChanged();
    }
}
