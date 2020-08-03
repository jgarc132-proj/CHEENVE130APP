package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button PhaseGamesButton = (Button) findViewById(R.id.PhaseGamesButton);
        PhaseGamesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityPhaseGames.class);
                view.getContext().startActivity(intent);
            }
        });



    }
}