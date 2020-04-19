package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.home_appliances;

import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.exceptions.checked.ApplianceIsOffException;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Brand;

public class WashingMachine extends HomeAppliances {

    private transient int rpm;

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
                throw new ApplianceIsOffException();
            } catch (ApplianceIsOffException e) {
                e.showTurnMeOnMessage(this);
            } finally {
                this.turnOn();
            }
        }
    }
}
