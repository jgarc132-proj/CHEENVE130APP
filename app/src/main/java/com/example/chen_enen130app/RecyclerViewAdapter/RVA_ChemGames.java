package com.example.chen_enen130app.RecyclerViewAdapter;

import java.lang.String;
import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chen_enen130app.DataFiles.ChemEquilParameters;
import com.example.chen_enen130app.R;

public class RVA_ChemGames extends RecyclerView.Adapter<RVA_ChemGames.CustomViewHolder> {

    private Context context;
    private int index[];
    private ChemEquilParameters[] cEPArray;
    private ArrayList<String> chemicalSpecies;

    public RVA_ChemGames(Context context, int index[], ArrayList<String> chemicalSpecies) {
        this.context = context;
        this.index = index;
        this.chemicalSpecies = chemicalSpecies;
        cEPArray = new ChemEquilParameters[index.length];
        cEPArrayPopulate();
    }

    private void cEPArrayPopulate() {
        for(int i = 0; i < index.length; ++i) {
            cEPArray[i] = new ChemEquilParameters();
        }
    }

    public ChemEquilParameters[] returnCEPArray() {
        return cEPArray;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_rpcardlet, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVA_ChemGames.CustomViewHolder holder, final int position) {
        holder.speciesNumber.setText(String.valueOf(index[position]));

        setUpArrayAdapter(holder);
        holder.species.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
                if(spinnerPosition == 0) {
                    cEPArray[position].setPosition(null);
                }
                else {
                    cEPArray[position].setPosition(spinnerPosition);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.stoichCoeff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(holder.stoichCoeff.getText().toString().length() != 0) {
                    try {
                        cEPArray[position].setStoichCoeff(Double.parseDouble(holder.stoichCoeff.getText().toString()));
                        Log.e(null, "" + cEPArray[position].getStoichCoeff());
                    }
                    catch(NumberFormatException e) {
                        cEPArray[position].setStoichCoeff(null);
                        if(cEPArray[position].getStoichCoeff() == null) {
                            Log.e(null, "Null value");
                        }
                    }
                }
                else {
                    cEPArray[position].setStoichCoeff(null);
                    if(cEPArray[position].getStoichCoeff() == null) {
                        Log.e(null, "Null value");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setUpArrayAdapter(RVA_ChemGames.CustomViewHolder holder) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, chemicalSpecies);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.species.setAdapter(arrayAdapter);
    }

    @Override
    public int getItemCount() {
        return index.length;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView speciesNumber;
        Spinner species;
        EditText stoichCoeff;

        public CustomViewHolder(@NonNull View view) {
            super(view);

            speciesNumber = view.findViewById(R.id.RPC_SpeciesNumber);
            species = view.findViewById(R.id.RPC_SpeciesSpinner);
            stoichCoeff = view.findViewById(R.id.RPC_StoicCoeff_EditText);
        }
    }
}
