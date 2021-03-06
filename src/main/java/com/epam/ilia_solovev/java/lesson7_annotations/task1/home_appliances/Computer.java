package com.epam.ilia_solovev.java.lesson7_annotations.task1.home_appliances;

import com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations.NoWiFi;
import com.epam.ilia_solovev.java.lesson7_annotations.task1.utils.Brand;

@NoWiFi
public class Computer extends HomeAppliances implements Connectible {

    public Computer(int powerConsumption, Brand brand, String model) {
        super(powerConsumption, brand, model);
    }

    @Override
    public void doWork() {
        if (this.isOn()) {
            System.out.println("I'm a Computer - Crunching numbers");
        } else {
            System.out.println("First of all, you need to turn me on");
        }
    }

    @Override
    public void connectToWiFi() {
        System.out.println("The Computer is connecting to the WiFi...");
    }
}
