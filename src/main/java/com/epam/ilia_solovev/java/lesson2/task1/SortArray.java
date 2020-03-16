package com.epam.ilia_solovev.java.lesson2.task1;

public class SortArray {

    public static void bubbleSortByPowerConsumption (HomeAppliances[] array) {

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
    }

    private static void swap (HomeAppliances[] array, int indexOne, int indexTwo) {
        HomeAppliances temp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = temp;
    }
}
