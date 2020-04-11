package com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.home_appliances;

import com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.exceptions.checked.ApplianceIsOffException;
import com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.exceptions.checked.WrongScreenSizeException;
import com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.utils.Brand;

public class TV extends HomeAppliances implements Connectible {

    private transient int screenSize;
    private transient int defaultScreenSize = 32;

    public TV(int powerConsumption, Brand brand, String model, int screenSize) throws WrongScreenSizeException {
        super(powerConsumption, brand, model);

        if (screenSize <= 0) {
            try {
                throw new WrongScreenSizeException();
            } finally {
                this.screenSize = defaultScreenSize;
            }
        } else {
            this.screenSize = screenSize;
        }
    }

    @Override
    public void doWork() {
        if (this.isOn()) {
            System.out.println("I'am a TV - Showing you Game of Thrones. It is so nice to watch it on a " + screenSize +
                    " inches screen, isn't?");
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
        System.out.println("The TV is connecting to the WiFi...");
    }
}
