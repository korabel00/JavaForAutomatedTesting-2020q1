package com.epam.ilia_solovev.java.lesson7_annotations.task1.home_appliances;

import com.epam.ilia_solovev.java.lesson7_annotations.task1.utils.Brand;

public class WashingMachine extends HomeAppliances {

    private int rpm;

    public WashingMachine(int powerConsumption, Brand brand, String model, int rpm) {
        super(powerConsumption, brand, model);
        this.rpm = rpm;
    }

    @Override
    public void doWork() {
        if (this.isOn()) {
            System.out.println("I'm a Washing Machine - Washing your cloth with " + rpm + " RPM. And when I am done " +
                    "I will turn off automatically");
        } else {
            System.out.println("First of all, you need to turn me on");
        }
        this.turnOff(); // turn off after ending of the job.
    }
}
