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

package com.epam.ilia_solovev.java.lesson4_streams_and_collections.task3_streams;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaCollectionStream {

    private List<Integer> integerArrayList;

    public void runCollection() {

        integerArrayList = generateCollection();
        integerArrayList = shuffleCollection(integerArrayList);
        showOrder(integerArrayList);
        showDistinct(integerArrayList);
        findMin(integerArrayList);
        integerArrayList = removeOdd(integerArrayList);
        findPenultimate(integerArrayList);
    }

    public List<Integer> generateCollection() {

        //1.Сгенерируйте 1 000 000 последовательных целых чисел
        return IntStream
                .range(1, 1_000_001)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> shuffleCollection(List<Integer> integerArrayList) {

        //2.Создайте коллекцию A и вставьте туда элементы последовательности в произвольном порядке*/
        Collections.shuffle(integerArrayList);
        return integerArrayList;
    }

    public void showOrder(List<Integer> integerArrayList) {

        //3. Покажите, что порядок произвольный
        System.out.print("First 10 elements are not in sequential order: ");
        integerArrayList.stream()
                .limit(10)
                .forEach((a) -> System.out.print(a + ", "));
        System.out.println();
    }

    public void showDistinct(List<Integer> integerArrayList) {

        //4.Напишите проверку на то, что все элементы в данной коллекции A уникальны (докажите это каким-либо способом)
        System.out.println("Number of distinct elements is: " + integerArrayList.stream()
                .distinct()
                .count());
    }

    public void findMin(List<Integer> integerArrayList) {

        // 5.Найдите минимальный элемент в данной последовательности
        System.out.println("Min element is: " + integerArrayList.stream()
                .min(Integer::compareTo)
                .orElse(null));
    }

    public List<Integer> removeOdd(List<Integer> integerArrayList) {

        //6.Удалите все нечетные элементы из последовательности
        integerArrayList = integerArrayList.stream()
                .filter(i -> (i % 2) == 0).collect(Collectors.toList());

        System.out.print("First 10 even elements: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(integerArrayList.get(i) + ", ");
        }
        System.out.println("and the size now is half of the original: " + integerArrayList.size());

        return integerArrayList;
    }

    public void findPenultimate(List<Integer> integerArrayList) {

        //7.Найдите предпоследний по величине элемент
        System.out.println("Penultimate element is: " + integerArrayList.stream()
                .sorted()
                .skip(integerArrayList.size() - 2)
                .findFirst()
                .orElse(null));
    }
}
