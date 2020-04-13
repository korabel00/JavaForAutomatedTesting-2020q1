package com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.utils;

import com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.home_appliances.HomeAppliances;

public class SortArray {

    public static void bubbleSortByPowerConsumption(HomeAppliances[] array) {

        boolean needIteration = true;

        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i].getPowerConsumption() < array[i - 1].getPowerConsumption()) {
                    swap(array, i, i - 1);
                    needIteration = true;
                }
            }
        }
        output(array);
    }

    private static void swap(HomeAppliances[] array, int indexOne, int indexTwo) {
        HomeAppliances temp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = temp;
    }

    private static void output(HomeAppliances[] array) {
        //And output the result with class names
        System.out.println("Sorted list of home appliances by power consumption (ascending):");
        for (HomeAppliances appliances : array) {
            System.out.println(appliances.getClass().getSimpleName() + " " + appliances.getBrand() + " " + appliances.getModel() + ", power: "
                    + appliances.getPowerConsumption());
        }
    }
}
