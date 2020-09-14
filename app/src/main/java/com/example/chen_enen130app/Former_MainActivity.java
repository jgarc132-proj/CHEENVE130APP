/*
package com.example.chen_enen130app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chen_enen130app.Other.BottomBarAdapter;
import com.example.chen_enen130app.Other.NoSwipePager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;
    BottomNavigationView BottomNav;

    Fragment theGame = new Fragment_LessonSelect();
    Fragment thermoFunCalc = new Fragment_ThermoFunCalc();
    Fragment profile = new ProfileFragment();

    Fragment currentFragment;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNav = findViewById(R.id.BottomBarNavigation);
        viewPager = findViewById(R.id.viewPager);

        //optimization
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPagingEnabled(false);

        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());
        pagerAdapter.addFragments(theGame);
        pagerAdapter.addFragments(thermoFunCalc);
        pagerAdapter.addFragments(profile);
        viewPager.setAdapter(pagerAdapter);

        BottomNav.setOnNavigationItemSelectedListener(navListener);

        //fragmentManager.beginTransaction().replace(R.id.FragmentContainer, new Fragment_ThermoFunCalc()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
*/
/*            int currentItem = item.getItemId();

            switch(currentItem)
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
            }*//*

            switch (item.getItemId())
            {
                case R.id.OptionGame:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.OptionCalculator:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.OptionProfile:
                    viewPager.setCurrentItem(2);
                    return true;
            }

           //getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer,selectedFragment).commit();
            return false;
        }
    };
}*/
