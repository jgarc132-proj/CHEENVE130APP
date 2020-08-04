package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPhaseGames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_games);

        Button RLButton = (Button) findViewById(R.id.RLButton);
        RLButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RaoultsLawCalculator.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}