package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chen_enen130app.DataFiles.Model_Chapter;
import com.example.chen_enen130app.RecyclerViewAdapter.RVA_Lesson_Outer;

import java.util.ArrayList;

public class Activity_Lesson extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model_Chapter> chapters;
    RVA_Lesson_Outer rVALessonOuter;
    int numChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        initializeViews();

        numChapters = 5;

        chapters = Model_Chapter.generateChapters(numChapters, generateLessons(numChapters));

        setOuterRecyclerView();
    }

    public void initializeViews() {
        recyclerView = findViewById(R.id.Outer_RVA);
    }

    /**
     *  A dummy method to create an arbitrary amount of lessons per chapter, with each index + 1 equating to
     *  the chapter number (e.g. index 0 = Chapter 1). Can be changed to generate specific lesson numbers for
     *  each chapter.
     */
    public ArrayList<Integer> generateLessons(int numChapters) {
        ArrayList<Integer> lessons = new ArrayList<>();

        for(int i = 0; i < numChapters; ++i) {
            lessons.add(i + 1);
        }

        return lessons;
    }

    private void setOuterRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rVALessonOuter = new RVA_Lesson_Outer(this, chapters);
        recyclerView.setAdapter(rVALessonOuter);
    }
}