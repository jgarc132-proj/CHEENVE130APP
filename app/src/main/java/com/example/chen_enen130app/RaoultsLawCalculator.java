package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chen_enen130app.DatabaseAccessibility.DatabaseAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RaoultsLawCalculator extends AppCompatActivity {

    Button RLCSolve;
    EditText InputValue1, InputValue2;
    Spinner ChemSpecies1, ChemSpecies2, Info1, Info2, Units1, Units2;

    List<String> RLCInfo = new ArrayList<>(), PressureUnits = new ArrayList<>(),
            TemperatureUnits = new ArrayList<>();
    ArrayList<String> ChemicalSpecies = new ArrayList<>();
    ArrayList<Double> A = new ArrayList<>(), B = new ArrayList<>(), C = new ArrayList<>();
    int CS1Position, CS2Position;
    Double IV1Value, IV2Value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raoults_law_calculator);

        DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
        dbAccess.open();

        initializeArrayData(dbAccess);
        RLCInfo = Arrays.asList(getResources().getStringArray(R.array.RLCInfo));
        PressureUnits = Arrays.asList(getResources().getStringArray(R.array.pressureUnits));
        TemperatureUnits = Arrays.asList(getResources().getStringArray(R.array.temperatureUnits));

        initializeViews();
        setupSpinners();

        retrieveVariables();
    }

    private void initializeArrayData(DatabaseAccess databaseAccess) {
        ChemicalSpecies.add("Select...");
        databaseAccess.PopulateArrayString("ANTOINE", "CHEMICAL_SPECIES", ChemicalSpecies);

        A.add(null);
        databaseAccess.PopulateArrayDouble("ANTOINE", "A", A);

        B.add(null);
        databaseAccess.PopulateArrayDouble("ANTOINE", "A", B);

        C.add(null);
        databaseAccess.PopulateArrayDouble("ANTOINE", "A", C);
    }

    private void initializeViews() {

        RLCSolve = (Button) findViewById(R.id.RLCButtonSolve);

        InputValue1 = (EditText) findViewById(R.id.RLCThirdQ1Edit);
        InputValue2 = (EditText) findViewById(R.id.RLCThirdQ2Edit);

        ChemSpecies1 = (Spinner) findViewById(R.id.RLCFirstQ1Spinner);
        ChemSpecies2 = (Spinner) findViewById(R.id.RLCFirstQ2Spinner);

        Info1 = (Spinner) findViewById(R.id.RLCSecondQ1Spinner);
        Info2 = (Spinner) findViewById(R.id.RLCSecondQ2Spinner);
        Units1 = (Spinner) findViewById(R.id.RLCThirdQ1UnitSelect);
        Units2 = (Spinner) findViewById(R.id.RLCThirdQ2UnitSelect);
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> Info1Adapter = ArrayAdapter.createFromResource(this, R.array.RLCInfo, android.R.layout.simple_spinner_item);
        Info1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Info1.setAdapter(Info1Adapter);

        ArrayAdapter<CharSequence> Info2Adapter = ArrayAdapter.createFromResource(this, R.array.RLCInfo, android.R.layout.simple_spinner_item);
        Info2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Info2.setAdapter(Info2Adapter);

        ArrayAdapter<String> ChemSpecies1Adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ChemicalSpecies);
        ChemSpecies1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChemSpecies1.setAdapter(ChemSpecies1Adapter);

        ArrayAdapter<String> ChemSpecies2Adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ChemicalSpecies);
        ChemSpecies2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChemSpecies2.setAdapter(ChemSpecies2Adapter);
    }

    private void retrieveVariables() {
        Info1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position) {
                    case 0:
                        InputValue1.setHint("");

                        Units1.setVisibility(View.GONE);
                        break;
                    case 1:
                        InputValue1.setHint(Html.fromHtml("x<sub><small><small>1</small></small></sub>"));

                        Units1.setVisibility(View.GONE);
                        break;
                    case 2:
                        InputValue1.setHint(Html.fromHtml("x<sub><small><small>2</small></small></sub>"));

                        Units1.setVisibility(View.GONE);
                        break;
                    case 3:
                        InputValue1.setHint(Html.fromHtml("y<sub><small><small>1</small></small></sub>"));

                        Units1.setVisibility(View.GONE);
                        break;
                    case 4:
                        InputValue1.setHint(Html.fromHtml("y<sub><small><small>2</small></small></sub>"));

                        Units1.setVisibility(View.GONE);
                        break;
                    case 5:
                        InputValue1.setHint(RLCInfo.get(position));

                        String[] arrayUnitsPressure = getResources().getStringArray(R.array.pressureUnits);
                        final ArrayAdapter<CharSequence> ArrayAdapterUnitsP = new ArrayAdapter<CharSequence>(RaoultsLawCalculator.this, android.R.layout.simple_spinner_item, arrayUnitsPressure);
                        ArrayAdapterUnitsP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Units1.setAdapter(ArrayAdapterUnitsP);
                        Units1.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        InputValue1.setHint(RLCInfo.get(position));

                        String[] arrayUnitsTemperature = getResources().getStringArray(R.array.temperatureUnits);
                        final ArrayAdapter<CharSequence> ArrayAdapterUnitsT = new ArrayAdapter<CharSequence>(RaoultsLawCalculator.this, android.R.layout.simple_spinner_item, arrayUnitsTemperature);
                        ArrayAdapterUnitsT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Units1.setAdapter(ArrayAdapterUnitsT);
                        Units1.setVisibility(View.VISIBLE);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Info2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position) {
                    case 0:
                        InputValue2.setHint("");

                        Units2.setVisibility(View.GONE);
                        break;
                    case 1:
                        InputValue2.setHint(Html.fromHtml("x<sub><small><small>1</small></small></sub>"));

                        Units2.setVisibility(View.GONE);
                        break;
                    case 2:
                        InputValue2.setHint(Html.fromHtml("x<sub><small><small>2</small></small></sub>"));

                        Units2.setVisibility(View.GONE);
                        break;
                    case 3:
                        InputValue2.setHint(Html.fromHtml("y<sub><small><small>1</small></small></sub>"));

                        Units2.setVisibility(View.GONE);
                        break;
                    case 4:
                        InputValue2.setHint(Html.fromHtml("y<sub><small><small>2</small></small></sub>"));

                        Units2.setVisibility(View.GONE);
                        break;
                    case 5:
                        InputValue2.setHint(RLCInfo.get(position));

                        String[] arrayUnitsPressure = getResources().getStringArray(R.array.pressureUnits);
                        final ArrayAdapter<CharSequence> ArrayAdapterUnitsP = new ArrayAdapter<CharSequence>(RaoultsLawCalculator.this, android.R.layout.simple_spinner_item, arrayUnitsPressure);
                        ArrayAdapterUnitsP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Units2.setAdapter(ArrayAdapterUnitsP);
                        Units2.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        InputValue2.setHint(RLCInfo.get(position));

                        String[] arrayUnitsTemperature = getResources().getStringArray(R.array.temperatureUnits);
                        final ArrayAdapter<CharSequence> ArrayAdapterUnitsT = new ArrayAdapter<CharSequence>(RaoultsLawCalculator.this, android.R.layout.simple_spinner_item, arrayUnitsTemperature);
                        ArrayAdapterUnitsT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Units2.setAdapter(ArrayAdapterUnitsT);
                        Units2.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RLCSolve = (Button) findViewById(R.id.RLCButtonSolve);
        RLCSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CS1Position = ChemSpecies1.getSelectedItemPosition();
                CS2Position = ChemSpecies2.getSelectedItemPosition();
                IV1Value = Double.parseDouble(InputValue1.getText().toString());
                IV2Value = Double.parseDouble(InputValue2.getText().toString());
            }
        });
    }
}
