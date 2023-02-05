package com.example.hw03;

import java.util.Date;

public class Drink
{
    private double drinkSize;
    private Date addedOn;

    private double valueA, alcPercent;

    public double getAlcPercent()
    {
        return alcPercent;
    }

    public void setAlcPercent(double alcPercent)
    {
        this.alcPercent = alcPercent;
    }

    public double getValueA()
    {
        return valueA;
    }

    public void setValueA(double valueA)
    {
        this.valueA = valueA;
    }

    public Drink(double drinkSize, double alcPercent)
    {
        this.drinkSize = drinkSize;
        this.addedOn = new Date();
        this.alcPercent = alcPercent;
        this.valueA = alcPercent * (drinkSize / 100);
    }

    public Drink()
    {
    }

    public double getDrinkSize()
    {
        return drinkSize;
    }

    public void setDrinkSize(double drinkSize)
    {
        this.drinkSize = drinkSize;
    }

    public Date getAddedOn()
    {
        return addedOn;
    }

    public void setAddedOn(Date addedOn)
    {
        this.addedOn = addedOn;
    }
}
