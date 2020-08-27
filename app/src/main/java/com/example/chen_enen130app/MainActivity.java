package com.example.chen_enen130app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    final Fragment theGame = new Fragment_LessonSelect();
    final Fragment thermoFunCalc = new Fragment_ThermoFunCalc();
    final Fragment profile = new ProfileFragment();

    Fragment currentFragment;

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
                    selectedFragment = theGame;
                    break;
                case R.id.OptionCalculator:
                    selectedFragment = thermoFunCalc;
                    break;
                case R.id.OptionProfile:
                    selectedFragment = profile;
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer,selectedFragment).commit();
            return true;
        }
    };
}