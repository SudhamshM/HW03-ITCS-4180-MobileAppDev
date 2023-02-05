package com.example.hw03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BacCalculatorFragment.BacHomeListener
        , SetProfileFragment.SetProfileListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new BacCalculatorFragment(), "home-fragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void goSetWeight()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SetProfileFragment(), "set-profile-fragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void addDrinks()
    {

    }

    @Override
    public void viewDrinks()
    {

    }

    @Override
    public void setProfile(Profile profile)
    {
        BacCalculatorFragment homeFragment = (BacCalculatorFragment) getSupportFragmentManager().findFragmentByTag("home-fragment");
        if (homeFragment != null)
        {
            profile.addDrink(new Drink(1, 30));
            homeFragment.setmUserProfile(profile);
            getSupportFragmentManager().popBackStack();

        }
    }

    @Override
    public void cancelSetWeight()
    {
        getSupportFragmentManager().popBackStack();
    }
}