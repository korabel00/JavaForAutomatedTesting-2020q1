/*
 * Ilia Solovev. Task 2-1
 *
 * Спроектировать объектную модель для заданной предметной области.
 * Домашняя техника. Определить иерархию ДТ. Включить некоторые в
 * розетку. Посчитать потребляемую мощность. Провести сортировку
 * приборов в квартире на основе одного из параметров. Найти кухонный
 * прибор в квартире, соответствующий заданному диапазону параметров.
 */

package com.epam.ilia_solovev.java.lesson2.task1;

public class RunHomeAppliance {

    public static void main(String[] args) {
        RunHomeAppliance app = new RunHomeAppliance();
        app.startApp();
    }

    //Running an application
    public void startApp() {

        int currentPowerConsumptionOfAllHome = 0;

        //Creating stuff for our home
        TV tv = new TV(200,"Samsung UE50NU7097U", 47);
        WashingMachine washingMachine = new WashingMachine(150, "Indesit IWUB 4085", 1000);
        Computer computer = new Computer(400, "DELL Vostro 3670");

        HomeAppliances[] homeAppliances = new HomeAppliances[3];
        homeAppliances[0] = tv;
        homeAppliances[1] = washingMachine;
        homeAppliances[2] = computer;

        for (HomeAppliances thing: homeAppliances) {
            //Every thing needs to be turned on - otherwise it won't work
            thing.turnOn();
            //Connecting every connectible thing to the WiFi
            if (thing instanceof Connectible) {
                ((Connectible) thing).connectToWiFi();
            }
            thing.doWork();
            //counting current home power consumption
            currentPowerConsumptionOfAllHome += thing.getPowerConsumptionWhenOn();
        }

        //600 is because the washing machine turned off automatically after finishing its work.
        System.out.println("Current power consumption is : " + currentPowerConsumptionOfAllHome);

        //Lets find all home appliances which are turned on and which power less or equal then 300
        for (HomeAppliances thing: homeAppliances) {
            if (thing.isOn() && thing.getPowerConsumption() <= 300) {
                System.out.println(thing.getModel());
            }
        }

        //Lets sort home appliances by power consumption
        SortArray.bubbleSortByPowerConsumption(homeAppliances);

        //And output the result with class names
        for (HomeAppliances thing: homeAppliances) {
            System.out.println(thing.getClass().getSimpleName());
        }
    }
}
