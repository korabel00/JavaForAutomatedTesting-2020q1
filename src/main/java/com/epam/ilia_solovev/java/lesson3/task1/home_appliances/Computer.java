package com.epam.ilia_solovev.java.lesson3.task1.home_appliances;

import com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked.ApplianceException;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Brand;

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
                throw new ApplianceException();
            } catch (ApplianceException e) {
                System.out.println(e.turnMeOnException(this));
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
