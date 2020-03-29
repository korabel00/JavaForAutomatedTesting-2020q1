/*
 * Ilia Solovev. Task 3-1
 *
 * Спроектировать иерархию исключительных ситуаций для объектной модели,
 * выбранной в предыдущем домашнем задании. В работе в обязательном порядке
 * должен быть реализован ВЕСЬ требующийся в задании функционал. Критерии
 * оценки:
 * a. Использовать: наследование от корректного класса, собственные
 * специфические методы, корректное отнесение к Checked/Unchecked exceptions – 2
 * балла
 * b. Каждый класс, метод и переменная должны иметь исчерпывающее смысл
 * название и информативный состав. Необходимо точно продумать, какие классы
 * необходимы для решения задачи. – 0,5 балла
 * c. Наследование вашей иерархии исключений должно быть невозможно, за
 * исключением базового класса – 0,5 балла
 * d. Предоставлен тестовый код (не меньше 5 разных ситуаций) в методе main,
 * в котором во время вызовов методов объектов из ДЗ2 бросаются исключения,
 * исключения обрабатываются осмысленно, блоки try/catch/finally/multicatch
 * имеются. – 1,5 балла
 * e. Код должен быть отформатирован и соответствовать Java Code Convention.
 * – 0,5 балла
 */

package com.epam.ilia_solovev.java.lesson3.task1;

import com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked.ApplianceException;
import com.epam.ilia_solovev.java.lesson3.task1.home_appliances.*;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Brand;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Colorable;
import com.epam.ilia_solovev.java.lesson3.task1.utils.SortArray;

public class RunHomeAppliance implements Colorable {

    public static void main(String[] args) {
        RunHomeAppliance app = new RunHomeAppliance();
        app.startApp();
    }

    //Running an application
    public void startApp() {

        // 1 case of exceptions (Checked) - You cannot create an appliance with power <= 0
        int[] powerOfThings = new int[]{0, 200, 400};
        int powerToCompare = 5;
        int numberOfAppliance = 3;

        HomeAppliances[] homeAppliances = new HomeAppliances[numberOfAppliance];
        createArrayOfHomeAppliance(homeAppliances, powerOfThings);
        makeThemWorkAndShowPowerConsumption(homeAppliances);

        // multicatch
        try {
            // 2 case of exceptions (Checked)- trying to create a TV with a screen size 0 - ApplianceException wll work
            TV tvLG = new TV(230, Brand.LG, "55sm8200pla", 0);
            // 3 case of exceptions (Unchecked)- trying to put 4th elements into array of 3
            // If you set screen siz > 0 than ArrayIndexOutOfBoundsException will work
            homeAppliances[3] = tvLG;
        } catch (ApplianceException e) {
            System.out.println(e.wrongScreenSize());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ANSI_RED + "You are trying to add an appliance, but there is no place for it: " +
                    e.getMessage() + ANSI_RESET);
        }

        // 4 case of exceptions (Checked)- you cannot make appliance work if it is not turned ON.
        homeAppliances[2].turnOff();
        homeAppliances[2].doWork();

        // 5 case of exceptions (Checked) - When there are no appliance with power <= powerToCompare
        showThingsWithPowerLessOrEqualThanParam(homeAppliances, powerToCompare);
        SortArray.bubbleSortByPowerConsumption(homeAppliances);
    }

    private void createArrayOfHomeAppliance(HomeAppliances[] homeAppliances, int[] powerOfThings) {

        TV tv = null;
        try {
            tv = new TV(powerOfThings[0], Brand.Samsung, "UE50NU7097U", 47);
        } catch (ApplianceException e) {
            e.printStackTrace();
        }
        WashingMachine washingMachine = new WashingMachine(powerOfThings[1], Brand.Indesit, "IWUB 4085", 1000);
        Computer computer = new Computer(powerOfThings[2], Brand.DELL, "Vostro 3670");

        homeAppliances[0] = tv;
        homeAppliances[1] = washingMachine;
        homeAppliances[2] = computer;
    }

    private void makeThemWorkAndShowPowerConsumption(HomeAppliances[] homeAppliances) {

        int currentPowerConsumption = 0;

        for (HomeAppliances thing : homeAppliances) {
            //Every thing needs to be turned on - otherwise it won't work
            thing.turnOn();
            //Connecting every connectible thing to the WiFi
            thing.doWork();
            if (thing instanceof Connectible) {
                ((Connectible) thing).connectToWiFi();
            }
            //counting current home power consumption
            currentPowerConsumption += thing.getPowerConsumptionWhenOn();
        }
        System.out.println("Showing you power consumption of all home...");
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
                throw new ApplianceException();
            } catch (ApplianceException e) {
                System.out.println(e.messageForBadCompare(powerToCompare));
            }
        }
    }
}
