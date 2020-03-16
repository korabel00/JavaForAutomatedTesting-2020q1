package com.epam.ilia_solovev.java.lesson2.task1;

public class WashingMachine extends HomeAppliances {

    private int rpm;

    public WashingMachine(int powerConsumption, String model, int rpm) {
        super(powerConsumption, model);
        this.rpm = rpm;
    }

    @Override
    public void doWork() {
        if (this.isOn()) {
            System.out.println("I'm a Washing Machine - Washing your cloth with " + rpm + " RPM. And when I am done " +
                    "I will turn off automatically");
        }
        else {
            System.out.println("First of all, you need to turn me on");
        }
        this.turnOff(); // turn off after ending of the job.
    }
}
