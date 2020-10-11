package com.example.chen_enen130app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Main extends AppCompatActivity {

    Button CourseOutlineButton, PhaseEquilButton, ChemEquilButton, GraphGenButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        buttonOnClickListener();
    }

    private void initializeViews() {
        CourseOutlineButton = findViewById(R.id.CourseOutlineButton);
        PhaseEquilButton = findViewById(R.id.PhaseEquilButton);
        ChemEquilButton = findViewById(R.id.ChemEquilButton);
        GraphGenButton = findViewById(R.id.GraphGenButton);
    }

    private void buttonOnClickListener() {

        CourseOutlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(), Activity_CourseOutline.class);
                startActivity(intent);
            }
        });

        PhaseEquilButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_PhaseGamesSelection.class);
                startActivity(intent);
            }
        });


        ChemEquilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_ChemGames.class);
                startActivity(intent);
            }
        });

        GraphGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizIntroduction.class);
                startActivity(intent);
            }
        });

    }


    /*    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View FragmentView = inflater.inflate(R.layout.activity_main, container, false);

        Button PhaseGamesButton = (Button) FragmentView.findViewById(R.id.PhaseGamesButton);
        PhaseGamesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.FragmentContainer,new Fragment_PhaseGamesSelection());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return FragmentView;
    }*/
}