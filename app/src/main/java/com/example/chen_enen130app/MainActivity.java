package com.example.chen_enen130app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView BottomNav = findViewById(R.id.BottomBarNavigation);
        BottomNav.setOnNavigationItemSelectedListener(navListener);

        fragmentManager.beginTransaction().replace(R.id.FragmentContainer, new Fragment_ThermoFunCalc()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId())
            {
                case R.id.OptionGame:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.OptionCalculator:
                    selectedFragment = new Fragment_ThermoFunCalc();
                    break;
                case R.id.OptionProfile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            fragmentManager.beginTransaction().replace(R.id.FragmentContainer, selectedFragment).commit();

            return true;

        }
    };
}