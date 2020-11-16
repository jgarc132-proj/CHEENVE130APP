package com.example.chen_enen130app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chen_enen130app.Lesson_RVA.ModelClass;
import com.example.chen_enen130app.Lesson_RVA.OuterRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class Activity_Lesson extends AppCompatActivity {

    RecyclerView outer_rv;
    List<ModelClass> list;
    OuterRVAdapter outerRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        outer_rv = findViewById(R.id.outer_rv);

        setOuterRecyclerView();
    }

    private List<ModelClass> getList() {
        list = new ArrayList<>();
        list.add(new ModelClass(R.mipmap.ic_launcher_round, "Chapter 10"));
        list.add(new ModelClass(R.mipmap.ic_launcher_round, "Chapter 11"));
        list.add(new ModelClass(R.mipmap.ic_launcher_round, "Chapter 12"));
        list.add(new ModelClass(R.mipmap.ic_launcher_round, "Chapter 13"));
        list.add(new ModelClass(R.mipmap.ic_launcher_round, "Chapter 14"));
        return list;
    }

    private void setOuterRecyclerView() {
        outer_rv.setHasFixedSize((true));
        outer_rv.setLayoutManager(new LinearLayoutManager(this));
        outerRVAdapter = new OuterRVAdapter(this, getList());
        outer_rv.setAdapter(outerRVAdapter);
    }
}