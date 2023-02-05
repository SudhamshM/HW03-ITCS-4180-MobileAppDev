package com.example.hw03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BacCalculatorFragment.BacHomeListener
        , SetProfileFragment.SetProfileListener,
        AddDrinkFragment.AddDrinkListener,
        ViewDrinksFragment.ViewDrinksListener
{
    Profile mainProfile = null;

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
    public void goToDrinks()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddDrinkFragment(), "add-drink-fragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToViewDrinks(ArrayList<Drink> userDrinks)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ViewDrinksFragment.newInstance(userDrinks), "view-drinks-fragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setProfile(Profile profile)
    {
        BacCalculatorFragment homeFragment = (BacCalculatorFragment) getSupportFragmentManager().findFragmentByTag("home-fragment");
        if (homeFragment != null)
        {
            homeFragment.setmUserProfile(profile);
            getSupportFragmentManager().popBackStack();
            mainProfile = homeFragment.getmUserProfile();
        }
    }

    @Override
    public void cancelSetWeight()
    {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void addDrink(Drink drink)
    {
        BacCalculatorFragment homeFragment = (BacCalculatorFragment) getSupportFragmentManager()
                .findFragmentByTag("home-fragment");
        if (homeFragment != null)
        {
            mainProfile = homeFragment.getmUserProfile();
            mainProfile.addDrink(drink);
            homeFragment.setmUserProfile(mainProfile);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelAddDrink()
    {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void closeDrinks()
    {
        ViewDrinksFragment viewFragment = (ViewDrinksFragment) getSupportFragmentManager()
                .findFragmentByTag("view-drinks-fragment");
        if (viewFragment != null)
        {
            mainProfile.setUserDrinks(viewFragment.getmUserDrinks());
            BacCalculatorFragment homeFragment = (BacCalculatorFragment) getSupportFragmentManager()
                    .findFragmentByTag("home-fragment");
            if (homeFragment != null)
            {
                homeFragment.setmUserProfile(mainProfile);

            }
            getSupportFragmentManager().popBackStack();
        }
    }

}