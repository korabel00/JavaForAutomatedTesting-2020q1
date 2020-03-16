package com.epam.ilia_solovev.java.lesson2.task1;

public abstract class HomeAppliances {

    private int powerConsumption;
    private int powerConsumptionWhenOn;
    private boolean on;
    private String model;

    public HomeAppliances(int powerConsumption, String model) {
        this.powerConsumption = powerConsumption;
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
}