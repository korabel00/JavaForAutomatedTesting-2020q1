package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.home_appliances;

import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.exceptions.checked.ApplianceIsOffException;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Brand;

public class Computer extends HomeAppliances implements Connectible {

    public Computer(int powerConsumption, Brand brand, String model) {
        super(powerConsumption, brand, model);
    }

    @Override
    public void doWork() {
        if (this.isOn()) {
            System.out.println("I'm a Computer - Crunching numbers");
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

    @Override
    public void connectToWiFi() {
        System.out.println("The Computer is connecting to the WiFi...");
    }
}
