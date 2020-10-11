package com.example.chen_enen130app;

import java.lang.String;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVA_ChemGames extends RecyclerView.Adapter<RVA_ChemGames.CustomViewHolder> {

    Context context;
    int index[];

    public RVA_ChemGames(Context context, int index[]) {
        this.context = context;
        this.index = index;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_rpcardlet, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVA_ChemGames.CustomViewHolder holder, final int position) {
        holder.speciesNumber.setText(String.valueOf(index[position]));
    }

    @Override
    public int getItemCount() {
        return index.length;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView speciesNumber;
        Spinner species;
        EditText stoichCoeff, molarAmount;

        public CustomViewHolder(@NonNull View view) {
            super(view);

            speciesNumber = view.findViewById(R.id.RPC_SpeciesNumber);
            species = view.findViewById(R.id.RPC_SpeciesSpinner);
            stoichCoeff = view.findViewById(R.id.RPC_StoicCoeff_EditText);
            molarAmount = view.findViewById(R.id.RPC_Moles_EditText);
        }
    }
}
