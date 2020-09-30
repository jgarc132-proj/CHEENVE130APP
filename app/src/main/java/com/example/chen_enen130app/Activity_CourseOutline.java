package com.example.chen_enen130app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Activity_CourseOutline extends AppCompatActivity {

    RecyclerView recyclerView;

    String title[], description[];
    int flavorImages[] = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,};
    int courseMaterial[] = {R.drawable.week_01, R.drawable.week_02, R.drawable.week_03, R.drawable.week_04, R.drawable.week_05,
    R.drawable.week_06, R.drawable.week_07, R.drawable.week_08, R.drawable.week_09, R.drawable.week_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_outline);

        recyclerView = findViewById(R.id.recyclerView);

        title = getResources().getStringArray(R.array.Weekly_Outlines);
        description = getResources().getStringArray(R.array.Description);

        RVA_CourseOutline myAdapter = new RVA_CourseOutline(this, title, description, flavorImages, courseMaterial);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

    /*
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.activity_course_outline, container, false);
       Button Lesson1 = (Button) fragmentView.findViewById(R.id.Lesson1Button);
        Lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.FragmentContainer,new Fragment_Exercise());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return fragmentView;
    }
 */
