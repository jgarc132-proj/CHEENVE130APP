package com.example.chen_enen130app;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_PhaseGamesSelection extends Fragment {

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View FragmentView = inflater.inflate(R.layout.fragment_phase_games, container, false);

        Button RLButton = (Button) FragmentView.findViewById(R.id.RLButton);
        RLButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.FragmentContainer,new Fragment_RaoultsLawCalculator());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return FragmentView;
    }
}