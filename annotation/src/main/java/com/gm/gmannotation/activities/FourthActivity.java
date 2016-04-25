package com.gm.gmannotation.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.gm.gmannotation.R;
import com.gm.gmannotation.activities.adapter.TestAdapter;
import com.gm.gmannotation.activities.model.TestModel;
import com.gm.gmannotation.util.DividerItemDecoration;
import com.gm.gmannotation.util.TempModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_fourth)
public class FourthActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;
    @ViewById
    RecyclerView recyclerView;
    @ViewById
    ProgressBar progressView;

    TestAdapter adapter;

    List<TestModel> testModelArrayList = new ArrayList<>();

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new TestAdapter(FourthActivity.this, testModelArrayList);

        recyclerView.setAdapter(adapter);

        progressView.setVisibility(View.VISIBLE);
        loadData();
    }

    @Background(delay = 3000)
    public void loadData() {
        showList();
    }

    @UiThread
    public void showList() {
        progressView.setVisibility(View.GONE);
        adapter.addAll(TempModel.getTestModelData());
    }
}
