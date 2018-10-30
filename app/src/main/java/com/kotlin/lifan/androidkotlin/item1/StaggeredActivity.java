package com.kotlin.lifan.androidkotlin.item1;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.kotlin.lifan.androidkotlin.base.BaseActivity;
import com.kotlin.lifan.androidkotlin.R;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends BaseActivity {

    private List<String> data;
    private RecyclerView recyclerView;
    private StaggeredAdapter staggeredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        initData();

        staggeredAdapter = new StaggeredAdapter(getBaseContext(), data);
        recyclerView.setAdapter(staggeredAdapter);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

    }


    private void initData() {
        data = new ArrayList<>();
        for (int j=1 ;j<1024; j++){
            for (int i='A'; i<='Z'; i++){
                char c = (char) i;
                data.add(String.valueOf(c));
            }
        }
    }
}
