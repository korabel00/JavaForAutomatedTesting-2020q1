package com.epam.ilia_solovev.java.lesson7_annotations.task1.home_appliances;

import com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations.*;
import com.epam.ilia_solovev.java.lesson7_annotations.task1.utils.Brand;

public abstract class HomeAppliances {

    @Zero
    private int powerConsumption;
    private int powerConsumptionWhenOn;
    private boolean on;
    private Brand brand;
    @Empty
    private String model;


    public HomeAppliances(int powerConsumption, Brand brand, String model) {

        this.powerConsumption = powerConsumption;
        this.brand = brand;
        this.model = model;
    }

    public void turnOn() {
        this.on = true;
        this.powerConsumptionWhenOn = powerConsumption;
    }

    public void turnOff() {
        this.on = false;
        this.powerConsumptionWhenOn = 0;
    }

    // @CanItWork
    public abstract void doWork();

    public boolean isOn() {
        return on;
    }

    public String getModel() {
        return model;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public int getPowerConsumptionWhenOn() {
        return powerConsumptionWhenOn;
    }

    public Brand getBrand() {
        return brand;
    }
}

