package com.example.hw03;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable
{
    private String gender;
    private double weight, BAC, valueR;
    private ArrayList<Drink> userDrinks;

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public double getBAC()
    {
        return BAC;
    }

    public void setBAC(double BAC)
    {
        this.BAC = BAC;
    }

    public double getValueR()
    {
        return valueR;
    }

    public void setValueR(double valueR)
    {
        this.valueR = valueR;
    }

    public ArrayList<Drink> getUserDrinks()
    {
        return userDrinks;
    }

    public void setUserDrinks(ArrayList<Drink> userDrinks)
    {
        this.userDrinks = userDrinks;
    }

    public Profile(String gender, double weight)
    {
        this.gender = gender;
        this.weight = weight;
        this.valueR = gender.equals("Male") ? 0.73 : 0.66;
        this.userDrinks = new ArrayList<Drink>();
        this.setBAC(0);
    }

    public double calculateBAC()
    {
        double userBAC = 0;
        if (!userDrinks.isEmpty())
        {
            for (Drink drink: userDrinks)
            {
                double valueA = drink.getValueA();
                double topHalf = valueA * 5.18;
                userBAC += topHalf / (weight * valueR);
            }
            setBAC(userBAC);
        }
        return userBAC;
    }

    public Profile()
    {
    }

    @Override
    public String toString()
    {
        return "Profile{" +
                "gender='" + gender + '\'' +
                ", weight=" + weight +
                ", BAC=" + BAC +
                ", valueR=" + valueR +
                ", userDrinks=" + userDrinks +
                '}';
    }

    public void addDrink(Drink drink)
    {
        userDrinks.add(drink);
    }

    public void removeDrink(int index)
    {
        userDrinks.remove(index);
    }
}
