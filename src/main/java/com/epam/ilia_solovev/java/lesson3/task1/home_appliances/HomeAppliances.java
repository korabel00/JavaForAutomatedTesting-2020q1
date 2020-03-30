package com.epam.ilia_solovev.java.lesson3.task1.home_appliances;

import com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked.ApplianceException;
import com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked.ZeroPowerException;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Brand;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Colorable;

public abstract class HomeAppliances implements Colorable {

    private int powerConsumption;
    private int powerConsumptionWhenOn;
    private boolean on;
    private Brand brand;
    private String model;

    public HomeAppliances(int powerConsumption, Brand brand, String model) throws ArrayIndexOutOfBoundsException {

        int defaultPower = 10;

        System.out.println(ANSI_GREEN + "Creating " + this.getClass().getSimpleName() + " " + brand + " " + model + " Power: " +
                powerConsumption + "..." + ANSI_RESET);

        if (powerConsumption <= 0) {
            try {
                throw new ZeroPowerException();
            } catch (ZeroPowerException e) {
                System.out.println(e.showMessageIfPowerIsZero(this, defaultPower));
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
        System.out.println(ANSI_YELLOW + this.getClass().getSimpleName() + " is turning on..." + ANSI_RESET);
        this.powerConsumptionWhenOn = powerConsumption;
    }

    public void turnOff() {
        this.on = false;
        System.out.println(ANSI_BLACK + this.getClass().getSimpleName() + " is turning off..." + ANSI_RESET);
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

