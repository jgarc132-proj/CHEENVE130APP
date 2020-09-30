package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_CourseMaterial extends AppCompatActivity {
    ImageView cmImageView;

    String data1, data2;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_material);

        cmImageView = findViewById(R.id.ImageView_CourseContent);

        getData();

        cmImageView.setImageResource(myImage);
    }

    private void getData () {
        if(getIntent().hasExtra("courseMaterial")) {
            myImage = getIntent().getIntExtra("courseMaterial", 1);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}