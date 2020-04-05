/*
 * Ilia Solovev. Task 4-3
 * Повторите все указанные действия, но при помощи
 * Stream API
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

package com.epam.ilia_solovev.java.lesson4.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaCollectionStream {

    public static void main(String[] args) {

        JavaCollectionStream app = new JavaCollectionStream();
        app.startApp();
    }

    public void startApp() {

        /*1.Сгенерируйте 1 000 000 последовательных целых чисел
        2.Создайте коллекцию A и вставьте туда элементы последовательности в произвольном порядке*/
        List<Integer> integerArrayList = new ArrayList<>();

        for (int i = 1; i <= 1_000_000; i++) {
            integerArrayList.add(i);
        }

        Collections.shuffle(integerArrayList);

        //3. Покажите, что порядок произвольный
        System.out.print("First 10 elements are not in sequential order: ");
        integerArrayList.stream()
                .limit(10)
                .forEach((a) -> {
                    System.out.print(a + ", ");
                });
        System.out.println();

        //4.Напишите проверку на то, что все элементы в данной коллекции A уникальны (докажите это каким-либо способом)
        System.out.println("Number of distinct elements is: " + integerArrayList.stream()
                .distinct()
                .count());

        // 5.Найдите минимальный элемент в данной последовательности
        System.out.println("Min element is: " + integerArrayList.stream()
                .min(Integer::compareTo)
                .orElse(null));

        //6.Удалите все нечетные элементы из последовательности
        integerArrayList = integerArrayList.stream()
                .filter(i -> (i % 2) == 0).collect(Collectors.toList());

        System.out.print("First 10 even elements: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(integerArrayList.get(i) + ", ");
        }
        System.out.println("and the size now is half of the original: " + integerArrayList.size());

        //7.Найдите предпоследний по величине элемент

        int count = (int) integerArrayList.stream().count();

        System.out.println("Penultimate element is: " + integerArrayList.stream()
                .sorted()
                .skip(count - 2)
                .findFirst()
                .orElse(null));
    }
}
