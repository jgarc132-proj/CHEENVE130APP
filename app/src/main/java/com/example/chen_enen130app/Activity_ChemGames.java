package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_ChemGames extends AppCompatActivity {

    RecyclerView chemSpecies;
    Spinner speciesAmountSpinner;

    List<Integer> speciesAmountList = new ArrayList<>();
    ArrayAdapter<Integer> speciesAmountListAA;

    RVA_ChemGames rva_chemGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem_games);

        populateList();
        initializeViews();
        spinnerSetUp();
    }

    private void populateList() {
        for(int i = 3; i <= 10; ++i) {
            speciesAmountList.add(i);
        }
    }

    private void initializeViews() {
        chemSpecies = findViewById(R.id.RPRecyclerView);

        speciesAmountSpinner = findViewById(R.id.CEC_ChemSpecSpinner);
    }

    private void spinnerSetUp() {
        speciesAmountListAA = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, speciesAmountList);
        speciesAmountListAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciesAmountSpinner.setAdapter(speciesAmountListAA);

        speciesAmountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                int numberArray[] = generateArray(position);

                rva_chemGames = new RVA_ChemGames(getApplicationContext(), numberArray);
                chemSpecies.setAdapter(rva_chemGames);
                chemSpecies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        speciesAmountSpinner.setSelection(0);
    }


    private int[] generateArray(int index) {
        int indexArray[] = new int[index + 3];

        for(int i = 0; i < indexArray.length; ++i) {
            indexArray[i] = i + 1;
        }

        return indexArray;
    }
}