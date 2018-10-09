package com.avater.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.avater.myapplication.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.tittle)
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 55; i++) {
            data.add("" + (i + 1));
        }
        TypeAdapter adapter = new TypeAdapter(this, data, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerview.setLayoutManager(layoutManager);
        recylerview.setAdapter(adapter);
    }
}
