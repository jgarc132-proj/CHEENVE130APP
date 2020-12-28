package com.example.chen_enen130app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
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
import com.example.chen_enen130app.DatabaseAccessibility.ParametersDbA;
import com.example.chen_enen130app.RecyclerViewAdapter.RVA_ChemGames;

import java.util.ArrayList;
import java.util.List;

public class Activity_ChemGames extends AppCompatActivity {

    RecyclerView chemSpecies;
    Spinner speciesAmountSpinner;
    EditText temperatureTV;
    Button calculateCE;

    List<Integer> speciesAmountList = new ArrayList<>();
    ArrayAdapter<Integer> speciesAmountListAA;

    ArrayList<String> temperatureUnits = new ArrayList<>();
    Spinner temperatureUnitsSpinner;
    ArrayAdapter<String> temperatureUnitsAA;

    RVA_ChemGames rva_chemGames;

    ParametersDbA dbAccess;

    ArrayList<String> chemicalSpecies = new ArrayList<>(),
            HTML_Species = new ArrayList<>();
    ArrayList<Double> gibbs298K = new ArrayList<>(),
            enth298K = new ArrayList<>(),
            A = new ArrayList<>(),
            B = new ArrayList<>(),
            C = new ArrayList<>(),
            D = new ArrayList<>();
    final String TABLE_NAME = "Properties_Of_Gases";

    ChemEquilParameters[] cEPArray;

    Double temperature;
    Double temperatureK;
    String temperatureUnit;

    double gibbsEnergyRxn = 0, equilK = 0;

    Dialog calculationResult;
    TextView displayGibbsE, displayEquilC, displayTemperature;
    Button dismissCR;
    TableLayout speciesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem_games);

        dbAccess = ParametersDbA.getInstance(getApplicationContext());
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
        HTML_Species.add(null);
        gibbs298K.add(null);
        enth298K.add(null);
        A.add(null);
        B.add(null);
        C.add(null);
        D.add(null);

        dbAccess.PopulateArrayString(TABLE_NAME, "Chemical_Species", chemicalSpecies);
        dbAccess.PopulateArrayString(TABLE_NAME, "HTML_Chemical_Formula", HTML_Species);
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

        temperatureUnitsSpinner = findViewById(R.id.TemperatureSpinner);

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


        // For temperature units spinner
        for(String temp: getResources().getStringArray(R.array.temperatureUnits)) {
            temperatureUnits.add(temp);
        }

        temperatureUnitsAA = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, temperatureUnits);
        temperatureUnitsAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temperatureUnitsSpinner.setAdapter(temperatureUnitsAA);

        temperatureUnitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temperatureUnit = temperatureUnits.get(position);
                Log.e(null, "Temperature unit: " + temperatureUnit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        temperatureUnitsSpinner.setSelection(0);
        temperatureUnit = "K";
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
                        Log.e(null, "Temperature = " + temperature + temperatureUnit);
                    }
                    catch(NumberFormatException e) {
                        temperature = null;
                        if(temperature == null) {
                            Log.e(null, "Temperature is null");
                        }
                    }
                }
                else {
                    temperature = null;
                    if(temperature == null) {
                        Log.e(null, "Temperature is null");
                    }
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

                boolean areParametersValid = areParametersValid();
                boolean isTempeartureValid = isTemperatureValid();

                if(areParametersValid && isTempeartureValid) {
                    calculate();
                }
                else if(!areParametersValid && isTempeartureValid){
                    Toast.makeText(getApplicationContext(), "You are either missing some parameters for the calculation.", Toast.LENGTH_SHORT).show();
                }
                else if(!isTempeartureValid && areParametersValid) {
                    Toast.makeText(getApplicationContext(), "Temperature is either below absolute zero or not given.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "You are either missing some parameters for the calculation.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean areParametersValid() {
        for(int i = 0; i < cEPArray.length; ++i) {
            if(cEPArray[i].getPosition() == null ||
                    cEPArray[i].getStoichCoeff() == null) {
                return false;
            }
        }

        return true;
    }

    private boolean isTemperatureValid() {
        if(temperature == null) {
            return false;
        }

        if(temperatureUnit.equals("K")) {
            if(temperature < 0) {
                return false;
            }
        }
        else if(temperatureUnit.equals("°C")) {
            if(temperature + 273.15 < 0.00000001) {
                return false;
            }
        }
        else if(temperatureUnit.equals("°F")) {
            if(temperature + 459.67 < 0.00000001) {
                return false;
            }
        }
        return true;
    }

    private void calculate() {
        switch (temperatureUnit) {
            case "K":
                temperatureK = temperature;
                Log.e(null, "Is kelvin.");
                break;
            case "°C":
                temperatureK = temperature + 273.15;
                break;
            case "°F":
                temperatureK = (temperature + 459.67) * 5.0 / 9.0;
                break;
        }

        fillData();

        gibbsEnergyRxn = ChemEquilParameters.gibbsEnergyofReaction(cEPArray, temperatureK);
        equilK = ChemEquilParameters.equilibriumConstant(gibbsEnergyRxn, temperatureK);

        showCalculationResult();
    }

    private void fillData() {
        for(int i = 0; i < cEPArray.length; ++i) {
            cEPArray[i].setA(A.get(cEPArray[i].getPosition()));
            cEPArray[i].setB(B.get(cEPArray[i].getPosition()));
            cEPArray[i].setC(C.get(cEPArray[i].getPosition()));
            cEPArray[i].setD(D.get(cEPArray[i].getPosition()));

            cEPArray[i].setGibbs298K(gibbs298K.get(cEPArray[i].getPosition()));
            cEPArray[i].setEnth298K(enth298K.get(cEPArray[i].getPosition()));
        }
    }

    private void showCalculationResult() {
        calculationResult = new Dialog(this);
        calculationResult.setContentView(R.layout.calculation_result);
        Window window = calculationResult.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dismissCR = calculationResult.findViewById(R.id.dismissDialogue_Button);
        displayGibbsE = calculationResult.findViewById(R.id.GibbsEnergyResult_Value);
        displayEquilC = calculationResult.findViewById(R.id.EquilibriumConst_Value);
        displayTemperature = calculationResult.findViewById(R.id.Temperature_Value);

        // Table used to populate programatically from cEPArray
        speciesTable = calculationResult.findViewById(R.id.Species_TableLayout);

        populateTable();

        displayGibbsE.setText(String.format("%.3f", gibbsEnergyRxn / 1000) + " kJ/mol");
        displayEquilC.setText(String.format("%.5e", equilK));
        displayTemperature.setText(temperature + temperatureUnit);

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
                speciesName_TextView.setText(Html.fromHtml(HTML_Species.get(cEPArray[i - 1].getPosition())));
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