package com.example.chen_enen130app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Activity_PhaseGamesSelection extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_games);

        Button PhaseGamesButton = findViewById(R.id.RLButton);
        PhaseGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_RaoultsLawCalculator.class);
                startActivity(intent);
            }
        });

        Button gamesButton = findViewById(R.id.MRLButton);
        gamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizIntroduction.class);
                startActivity(intent);
            }
        });

    }
}

/*
public class Activity_PhaseGamesSelection extends Fragment {

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View FragmentView = inflater.inflate(R.layout.activity_phase_games, container, false);

        return FragmentView;
    }
}*/
