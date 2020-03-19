/*
 * Ilia Solovev. Task 1-1
 *
 * Написать программу, осуществляющую обработку массива. Исходные
 * условия: массив содержит только целые числа от -10 до 10 (допускается
 * генерация элементов с помощью метода (int) Math.random()), программа
 * должна выводить в консоль исходный массив и полученный результат,
 * количество элементов в массиве 20.
 * <p>
 * В массиве целых чисел поменять местами максимальный
 * отрицательный элемент и минимальный положительный.
 */

package com.epam.ilia_solovev.java.lesson1.task1;

public class ArrayProcess {

    //Creating the array with length 20
    private int[] arrayOfInts = new int[20];

    public static void main(String[] args) {
        ArrayProcess app = new ArrayProcess();
        app.startArrayProcess();
    }

    //Running an application
    public void startArrayProcess() {
        initializeAnArray();
        findAndSwitchMinPositiveAndMaxNegative();
        outputResult();
    }

    //an Array initialization with random values
    private void initializeAnArray() {

        int maxLimit = 10;
        int minLimit = -10;

        for (int i = 0; i < arrayOfInts.length; i++) {
            arrayOfInts[i] = (int) (Math.random() * ((maxLimit - minLimit) + 1)) + minLimit;
            System.out.print(arrayOfInts[i] + " ");
        }
    }

    //find Min Positive and Max Negative numbers in an Array and its position in the Array;
    private void findAndSwitchMinPositiveAndMaxNegative() {

        int minPositiveNumberPlace = 0;
        int maxNegativeNumberPlace = 0;
        int minPositiveNumberInArray = 11;
        int maxNegativeNumberInArray = 0;

        for (int i = 0; i < arrayOfInts.length; i++) {
            if (arrayOfInts[i] > 0 && arrayOfInts[i] < minPositiveNumberInArray) {
                minPositiveNumberInArray = arrayOfInts[i];
                minPositiveNumberPlace = i;
            }
            if (arrayOfInts[i] < maxNegativeNumberInArray) {
                maxNegativeNumberInArray = arrayOfInts[i];
                maxNegativeNumberPlace = i;
            }
        }
        arrayOfInts[minPositiveNumberPlace] = maxNegativeNumberInArray;
        arrayOfInts[maxNegativeNumberPlace] = minPositiveNumberInArray;
    }

    private void outputResult() {

        System.out.println();
        for (int arrayOfInt : arrayOfInts) {
            System.out.print(arrayOfInt + " ");
        }
    }
}