package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen_enen130app.DataFiles.ChemEquilParameters;
import com.example.chen_enen130app.DatabaseAccessibility.DatabaseAccess;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Activity_ChemGames extends AppCompatActivity {

    RecyclerView chemSpecies;
    Spinner speciesAmountSpinner;
    EditText temperatureTV;
    Button calculateCE;

    List<Integer> speciesAmountList = new ArrayList<>();
    ArrayAdapter<Integer> speciesAmountListAA;

    RVA_ChemGames rva_chemGames;

    DatabaseAccess dbAccess;

    ArrayList<String> chemicalSpecies = new ArrayList<>();
    ArrayList<Double> gibbs298K = new ArrayList<>(),
            enth298K = new ArrayList<>(),
            A = new ArrayList<>(),
            B = new ArrayList<>(),
            C = new ArrayList<>(),
            D = new ArrayList<>();
    final String TABLE_NAME = "Properties_Of_Gases";

    ChemEquilParameters[] cEPArray;
    Double temperature;

    double gibbsReaction = 0, equilK = 0;

    Dialog calculationResult;
    TextView displayGibbsE, displayEquilC;
    Button dismissCR;
    TableLayout speciesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem_games);

        dbAccess = DatabaseAccess.getInstance(getApplicationContext());
        dbAccess.open();
        fillArrayListData();
        dbAccess.close();

        populateList();
        initializeViews();
        spinnerSetUp();

        editTextSetUp();


        beforeCalculate();
    }

    private void populateList() {
        for(int i = 3; i <= 10; ++i) {
            speciesAmountList.add(i);
        }
    }

    private void fillArrayListData() {
        chemicalSpecies.add("Select...");
        gibbs298K.add(null);
        enth298K.add(null);
        A.add(null);
        B.add(null);
        C.add(null);
        D.add(null);

        dbAccess.PopulateArrayString(TABLE_NAME, "Chemical_Species", chemicalSpecies);
        dbAccess.PopulateArrayDouble(TABLE_NAME, "G_298", gibbs298K);
        dbAccess.PopulateArrayDouble(TABLE_NAME, "H_298", enth298K);
        dbAccess.PopulateArrayDouble(TABLE_NAME, "A", A);
        dbAccess.PopulateArrayDouble(TABLE_NAME, "B_1E3", B);
        dbAccess.PopulateArrayDouble(TABLE_NAME, "C_1E6", C);
        dbAccess.PopulateArrayDouble(TABLE_NAME, "D_1EN5", D);
    }

    private void initializeViews() {
        chemSpecies = findViewById(R.id.RPRecyclerView);
        speciesAmountSpinner = findViewById(R.id.CEC_ChemSpecSpinner);
        temperatureTV = findViewById(R.id.TemperatureEditText);
        calculateCE = findViewById(R.id.CEC_CalculateButton);

        calculationResult = new Dialog(this);
    }

    private void spinnerSetUp() {
        speciesAmountListAA = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, speciesAmountList);
        speciesAmountListAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciesAmountSpinner.setAdapter(speciesAmountListAA);

        speciesAmountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                int numberArray[] = generateArray(position);

                rva_chemGames = new RVA_ChemGames(getApplicationContext(), numberArray, chemicalSpecies);
                chemSpecies.setAdapter(rva_chemGames);
                chemSpecies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        speciesAmountSpinner.setSelection(0);
    }

    private void editTextSetUp() {
        temperatureTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(temperatureTV.getText().toString().length() != 0) {
                    try {
                        temperature = Double.parseDouble((temperatureTV.getText().toString()));
                    } catch(NumberFormatException e) {
                        temperature = null;
                    }
                }
                else {
                    temperature = null;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private int[] generateArray(int index) {
        int indexArray[] = new int[index + 3];

        for(int i = 0; i < indexArray.length; ++i) {
            indexArray[i] = i + 1;
        }

        return indexArray;
    }

    private void beforeCalculate() {
        calculateCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cEPArray = rva_chemGames.returnCEPArray();

                if(areParametersValid()) {
                    calculate();
                }
                else {
                    Toast.makeText(getApplicationContext(), "You are missing some parameters for the calculation.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean areParametersValid() {
        if(temperature == null) {
            return false;
        }

        for(int i = 0; i < cEPArray.length; ++i) {
            if(cEPArray[i].getPosition() == null ||
                    cEPArray[i].getStoichCoeff() == null) {
                return false;
            }
        }

        return true;
    }

    private void calculate() {
        int arraySize = cEPArray.length;
        double totalA = 0, totalB = 0, totalC = 0, totalD = 0, totalEnthalpy = 0, totalGibbs = 0;
        double enthalpyInt = 0, gibbsInt = 0;
        double GRT = 0;

        for(int i = 0; i < arraySize; ++i) {
            double coeff = cEPArray[i].getStoichCoeff();

            totalA += coeff * A.get(cEPArray[i].getPosition());
            totalB += coeff * B.get(cEPArray[i].getPosition());
            totalC += coeff * C.get(cEPArray[i].getPosition());
            totalD += coeff * D.get(cEPArray[i].getPosition());
            totalEnthalpy += coeff * enth298K.get(cEPArray[i].getPosition());
            totalGibbs += coeff * gibbs298K.get(cEPArray[i].getPosition());
        }

        totalB = totalB / Math.pow(10,3);
        totalC = totalB / Math.pow(10,6);
        totalD = totalB / Math.pow(10,-5);

        enthalpyInt = totalA * (temperature - 298.15);
        enthalpyInt += totalB/2 * (Math.pow(temperature, 2) - Math.pow(298.15, 2));
        enthalpyInt += totalC/3 * (Math.pow(temperature, 3) - Math.pow(298.15, 3));
        enthalpyInt += totalD * (temperature - 298.15)/(temperature * 298.15);

        Log.e(null, "Enthalpy integration: " + enthalpyInt);

        gibbsInt = totalA * Math.log(temperature/298.15);
        gibbsInt += (totalB + (totalC + totalD/(Math.pow(298.15, 2) * Math.pow(temperature, 2))) * ((temperature + 298.15)/2)) * (temperature - 298.15);

        GRT = (totalGibbs - totalEnthalpy)/(8.314 * 298.15) + (totalEnthalpy)/(8.314 * temperature);
        GRT += 1/(temperature) * enthalpyInt - gibbsInt;

        gibbsReaction = GRT * 8.314 * temperature;
        equilK = Math.exp(-GRT);

        Log.e(null, "Gibbs energy of reaction: " + gibbsReaction + ", Equilibrium constant: " + equilK);

        Toast.makeText(getApplicationContext(), "" + equilK, Toast.LENGTH_LONG);

        showCalculationResult();
    }

    private void showCalculationResult() {
        calculationResult = new Dialog(this);
        calculationResult.setContentView(R.layout.calculation_result);
        Window window = calculationResult.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dismissCR = calculationResult.findViewById(R.id.dismissDialogue_Button);
        displayGibbsE = calculationResult.findViewById(R.id.GibbsEnergyResult_Value);
        displayEquilC = calculationResult.findViewById(R.id.EquilibriumConst_Value);

        // Table used to populate programatically from cEPArray
        speciesTable = calculationResult.findViewById(R.id.Species_TableLayout);

        populateTable();

        displayGibbsE.setText(String.format("%.3f", gibbsReaction / 1000) + " kJ/mol");
        displayEquilC.setText(String.format("%.5e", equilK));

        dismissCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationResult.dismiss();
            }
        });

        calculationResult.show();
    }

    private void populateTable() {

        for(int i = 0; i <= cEPArray.length; ++i) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            row.setLayoutParams(layoutParams);

            TextView speciesNumber_TextView = new TextView(this),
                    speciesName_TextView = new TextView(this),
                    stoichCoeff_TextView = new TextView(this);

            if(i == 0) {
                speciesNumber_TextView.setText("#");
                speciesName_TextView.setText("Species");
                stoichCoeff_TextView.setText("Stoich. Coeff.");
            }
            else {
                speciesNumber_TextView.setText(Integer.toString(i));
                speciesName_TextView.setText(chemicalSpecies.get(cEPArray[i - 1].getPosition()));
                stoichCoeff_TextView.setText(cEPArray[i - 1].getStoichCoeff().toString());
            }

            speciesNumber_TextView.setGravity(Gravity.CENTER);
            speciesName_TextView.setGravity(Gravity.CENTER);
            stoichCoeff_TextView.setGravity(Gravity.CENTER);

            speciesNumber_TextView.setTextSize(16);
            speciesName_TextView.setTextSize(16);
            stoichCoeff_TextView.setTextSize(16);

            row.addView(speciesNumber_TextView);
            row.addView(speciesName_TextView);
            row.addView(stoichCoeff_TextView);

            speciesTable.addView(row, i);
        }
    }
}