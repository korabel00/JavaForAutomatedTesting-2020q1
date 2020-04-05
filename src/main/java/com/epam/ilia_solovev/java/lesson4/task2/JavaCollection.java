/*
 * Ilia Solovev. Task 4-2
 *
 * Необходимо проделать определенные трансформации с данными,
 * используя для это корректные коллекции и алгоритмы из Java Collection  * Framework
 * 1.Сгенерируйте 1 000 000 последовательных целых чисел
 * 2.Создайте коллекцию A и вставьте туда элементы
 * последовательности в произвольном порядке
 * 3.Покажите, что порядок произвольный
 * 4.Напишите проверку на то, что все элементы в данной
 * коллекции A уникальны (докажите это каким-либо
 * способом)
 * 5.Найдите минимальный элемент в данной последовательности
 * 6.Удалите все нечетные элементы из последовательности
 * 7.Найдите предпоследний по величине элемент */

package com.epam.ilia_solovev.java.lesson4.task2;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class JavaCollection {

    public static void main(String[] args) {

        JavaCollection app = new JavaCollection();
        app.startApp();
    }

    public void startApp() {

        /*1.Сгенерируйте 1 000 000 последовательных целых чисел
        2.Создайте коллекцию A и вставьте туда элементы последовательности в произвольном порядке*/
        List<Integer> integerArrayList = new ArrayList<>();

        for (int i = 1; i <= 1000000; i++) {
            integerArrayList.add(i);
        }
        Collections.shuffle(integerArrayList);

        //3. Покажите, что порядок произвольный
        System.out.print("First 10 elements are not in sequential order: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(integerArrayList.get(i) + ", ");
        }
        System.out.println();

        //4.Напишите проверку на то, что все элементы в данной коллекции A уникальны (докажите это каким-либо способом)
        Map<Integer, Boolean> integerBooleanHashMap= new HashMap<>();

        for (Integer integer : integerArrayList) {
            integerBooleanHashMap.put(integer, true);
        }

        // Размеры должны быть равны, потому что в map содержатся только уникальные ключи и их должно быть 1 млн
        System.out.println(integerArrayList.size() == integerBooleanHashMap.size() ? "equal " +
                integerBooleanHashMap.size(): "not equal " + integerBooleanHashMap.size());

        // 5.Найдите минимальный элемент в данной последовательности
        System.out.println("Min element is: " + Collections.min(integerArrayList));

        //6.Удалите все нечетные элементы из последовательности
        integerArrayList.removeIf(i -> (i % 2 != 0));
        System.out.print("First 10 even elements: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(integerArrayList.get(i) + ", ");
        }
        System.out.println("And the size now is half of the original: " + integerArrayList.size());

        //7.Найдите предпоследний по величине элемент
        int tempElement = integerArrayList.get(0);
        int maxElement = Collections.max(integerArrayList);

        for (Integer integer : integerArrayList) {
            if (integer != maxElement && integer > tempElement) {
                    tempElement = integer;
            }
        }
        System.out.println("Penultimate element is: " + tempElement);
    }
}
