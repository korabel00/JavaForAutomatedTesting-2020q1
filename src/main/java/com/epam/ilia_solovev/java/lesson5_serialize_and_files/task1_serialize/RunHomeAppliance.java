/*
 * Ilia Solovev. Task 3-1
 *
Добавьте для иерархии объектов из 2 задачи возможность
сериализоваться/десериализоваться. Однако все численные поля не
должны подвергаться этой процедуре. Предоставьте тестовый код, в
котором вы создаете несколько экземпляров различных классов этой
иерархии и успешно выполняете операцию
сериализации/десереализации.
 */

package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize;

import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.exceptions.checked.BadCompareException;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.exceptions.checked.WrongScreenSizeException;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.home_appliances.*;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Brand;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Color;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Serialize;

import java.io.IOException;

public class RunHomeAppliance {

    public static void main(String[] args) {
        RunHomeAppliance app = new RunHomeAppliance();
        try {
            app.startApp();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startApp() throws IOException, ClassNotFoundException {

        int[] powerOfThings = new int[]{100, 200, 400};
        int numberOfAppliance = 3;
        String fileForKeepingObjectStates = "output.txt";

        HomeAppliances[] homeAppliances = new HomeAppliances[numberOfAppliance];
        createArrayOfHomeAppliance(homeAppliances, powerOfThings);
        Serialize serialize = new Serialize();
        serialize.writeObjects(homeAppliances, fileForKeepingObjectStates);
        serialize.readObjects(fileForKeepingObjectStates);
    }

    private void createArrayOfHomeAppliance(HomeAppliances[] homeAppliances, int[] powerOfThings) {

        TV tv = null;
        try {
            tv = new TV(powerOfThings[0], Brand.Samsung, "UE50NU7097U", 47);
        } catch (WrongScreenSizeException e) {
            e.showWrongScreenSizeMessage();
        }
        WashingMachine washingMachine = new WashingMachine(powerOfThings[1], Brand.Indesit, "IWUB 4085", 1000);
        Computer computer = new Computer(powerOfThings[2], Brand.DELL, "Vostro 3670");

        homeAppliances[0] = tv;
        homeAppliances[1] = washingMachine;
        homeAppliances[2] = computer;
    }

    private void makeThemWorkAndShowPowerConsumption(HomeAppliances[] homeAppliances) {

        int currentPowerConsumption = 0;

        for (HomeAppliances appliance : homeAppliances) {
            //Every appliance needs to be turned on - otherwise it won't work
            appliance.turnOn();
            //Connecting every connectible appliance to the WiFi
            appliance.doWork();

            if (appliance instanceof Connectible) {
                ((Connectible) appliance).connectToWiFi();
            }
            //counting current home power consumption
            currentPowerConsumption += appliance.getPowerConsumptionWhenOn();
        }
        System.out.println(Color.ANSI_BLUE.getCode() + "Showing you power consumption of all home..." +
                Color.ANSI_RESET.getCode());
        System.out.println("Current power consumption is : " + currentPowerConsumption);
    }

    private void showThingsWithPowerLessOrEqualThanParam(HomeAppliances[] homeAppliances, int powerToCompare) {

        int count = 0;

        System.out.println("Trying to find things with power <= " + powerToCompare + "...");

        //Lets find all home appliances which are turned on and which power less or equal then param
        for (HomeAppliances thing : homeAppliances) {
            if (thing.isOn() && thing.getPowerConsumption() <= powerToCompare) {
                System.out.println("Home appliance with power less or equal than " + powerToCompare + " is: "
                        + thing.getClass().getSimpleName() + " " + thing.getBrand() + " " + thing.getModel());
                count++;
            }
        }

        if (count == 0) {
            try {
                throw new BadCompareException();
            } catch (BadCompareException e) {
                e.showMessageIfBadCompare(powerToCompare);
            }
        }
    }
}
