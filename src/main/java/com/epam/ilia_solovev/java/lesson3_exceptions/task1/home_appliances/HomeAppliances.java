package com.epam.ilia_solovev.java.lesson3_exceptions.task1.home_appliances;

import com.epam.ilia_solovev.java.lesson3_exceptions.task1.exceptions.checked.ZeroPowerException;
import com.epam.ilia_solovev.java.lesson3_exceptions.task1.utils.Brand;
import com.epam.ilia_solovev.java.lesson3_exceptions.task1.utils.Color;

public abstract class HomeAppliances {

    private int powerConsumption;
    private int powerConsumptionWhenOn;
    private boolean on;
    private Brand brand;
    private String model;

    public HomeAppliances(int powerConsumption, Brand brand, String model) throws ArrayIndexOutOfBoundsException {

        int defaultPower = 10;

        System.out.println(Color.ANSI_GREEN.getCode() + "Creating " + this.getClass().getSimpleName() + " " + brand + " " + model + " Power: " +
                powerConsumption + "..." + Color.ANSI_RESET.getCode());

        if (powerConsumption <= 0) {
            try {
                throw new ZeroPowerException();
            } catch (ZeroPowerException e) {
                e.showMessageIfPowerIsZero(this, defaultPower);
            } finally {
                powerConsumption = defaultPower; //default power
            }
        }
        this.powerConsumption = powerConsumption;
        this.brand = brand;
        this.model = model;
    }

    public void turnOn() {
        this.on = true;
        System.out.println(Color.ANSI_YELLOW.getCode() + this.getClass().getSimpleName() + " is turning on..." +
                Color.ANSI_RESET.getCode());
        this.powerConsumptionWhenOn = powerConsumption;
    }

    public void turnOff() {
        this.on = false;
        System.out.println(Color.ANSI_BLACK.getCode() + this.getClass().getSimpleName() + " is turning off..." +
                Color.ANSI_RESET.getCode());
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

    public Brand getBrand() {
        return brand;
    }
}

