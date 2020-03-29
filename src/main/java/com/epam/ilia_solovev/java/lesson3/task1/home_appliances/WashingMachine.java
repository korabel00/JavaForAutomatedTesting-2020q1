package com.epam.ilia_solovev.java.lesson3.task1.home_appliances;

import com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked.ApplianceException;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Brand;

import java.sql.SQLOutput;

public class WashingMachine extends HomeAppliances {

    private int rpm;

    public WashingMachine(int powerConsumption, Brand brand, String model, int rpm) {
        super(powerConsumption, brand, model);
        this.rpm = rpm;
    }

    @Override
    public void doWork() {
        if (this.isOn()) {
            System.out.println("I'm a Washing Machine - Washing your cloth with " + rpm + " RPM.");
        } else {
            try {
                throw new ApplianceException();
            } catch (ApplianceException e) {
                System.out.println(e.turnMeOnException(this));
            } finally {
                this.turnOn();
            }
        }
    }
}
