package com.epam.ilia_solovev.java.lesson2.task1;

public class Computer extends HomeAppliances implements Connectible{

    public Computer(int powerConsumption, String model) {
        super(powerConsumption, model);
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
