/*Ilia Solovev. Task 1, Option 1

Написать программу, осуществляющую обработку массива. Исходные
условия: массив содержит только целые числа от -10 до 10 (допускается
генерация элементов с помощью метода (int) Math.random()), программа
должна выводить в консоль исходный массив и полученный результат,
количество элементов в массиве 20.

В массиве целых чисел поменять местами максимальный
отрицательный элемент и минимальный положительный.

 */

package com.epam.ilia_solovev.java.leasson1.task1;

public class ArrayProcess {

    public static void main(String[] args) {
        ArrayProcess app = new ArrayProcess();
        app.startArrayProcess();
    }

    public void startArrayProcess() {
        int arraySize = 10;
        int maxLimit = 10;
        int minLimit = -10;
        int[] arrayOfInts = new int[arraySize];
        int maxNumberInArray = arrayOfInts[0];
        int minnNumberInArray = arrayOfInts[0];
        int maxNumberPlace = 0;
        int minNumberPlace = 0;

        //an Array initialization with random values
        for (int i = 0; i < arraySize; i++) {
            arrayOfInts[i] = (int) (Math.random() * ((maxLimit - minLimit) + 1)) + minLimit;
            System.out.println(arrayOfInts[i]);
        }

        //find Min and Aax numbers in an Array and its position in an Array;
        for (int i = 1; i < arraySize; i++) {
            if (arrayOfInts[i] > maxNumberInArray) {
                maxNumberInArray = arrayOfInts[i];
                maxNumberPlace = i;
            }
            if (arrayOfInts[i] < minnNumberInArray) {
                minnNumberInArray = arrayOfInts[i];
                minNumberPlace = i;
            }
        }


        System.out.println("maxNumber: " + maxNumberInArray + " Position :" + maxNumberPlace);
        System.out.println("minNumber: " + minnNumberInArray + " Position :" + minNumberPlace);
    }
}
