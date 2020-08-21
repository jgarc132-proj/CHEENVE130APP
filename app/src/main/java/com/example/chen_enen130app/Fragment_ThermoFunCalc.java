package com.example.chen_enen130app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment_ThermoFunCalc extends Fragment {

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View FragmentView = inflater.inflate(R.layout.fragment_thermofuncalc, container, false);

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
    }
}