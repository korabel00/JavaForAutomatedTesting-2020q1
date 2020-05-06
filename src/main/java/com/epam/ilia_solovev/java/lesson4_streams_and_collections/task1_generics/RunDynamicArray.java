/*
* Ilia Solovev. Task 4-1
*
* Создать собственный класс DynamicArray, содержащий внутри себя
* массив, поддерживающий добавление элемента в конец массива (add),
* получение элемента по индексу (get), печать внутреннего состояния
* (toString), удаление произвольного элемента по индексу (remove), а
* также возможность задать начальный размер при вызове конструктора.
* При изменении количества элементов внутренний массив должен
* пересоздаваться.*/

package com.epam.ilia_solovev.java.lesson4_streams_and_collections.task1_generics;

public class RunDynamicArray {

    public static void main(String[] args) {

        RunDynamicArray app = new RunDynamicArray();
        app.startDynamicArrayOfStrings();
        app.startDynamicArrayOfIntegers();
    }

    public void startDynamicArrayOfStrings() {

        int indexToGet = 2;
        int indexToRemove = 1;

        DynamicArray<String> dynamicArrayOfStrings = new DynamicArray<>();
        dynamicArrayOfStrings.add("First String"); //массив, поддерживающий добавление элемента в конец массива (add)
        dynamicArrayOfStrings.add("Middle String");
        dynamicArrayOfStrings.add("Last String");
        System.out.println(dynamicArrayOfStrings.getElement(indexToGet));//получение элемента по индексу (get)
        System.out.println(dynamicArrayOfStrings.toString()); // печать внутреннего состояния (toString)
        dynamicArrayOfStrings.remove(indexToRemove); //удаление произвольного элемента по индексу (remove)
        System.out.println(dynamicArrayOfStrings.toString());
        DynamicArray<String> dynamicArrayOfThreeStrings = new DynamicArray<>(3, new String[]{"Alex, Tomas, Victor"}); //возможность задать начальный размер
        System.out.println(dynamicArrayOfThreeStrings.toString());

    }

    public void startDynamicArrayOfIntegers() {

        int indexToGet = 0;
        int indexToRemove = 2;

        DynamicArray<Integer> dynamicArrayOfIntegers = new DynamicArray<>();
        dynamicArrayOfIntegers.add(1); //массив, поддерживающий добавление элемента в конец массива (add),
        dynamicArrayOfIntegers.add(2);
        dynamicArrayOfIntegers.add(3);
        System.out.println(dynamicArrayOfIntegers.getElement(indexToGet));//получение элемента по индексу (get),
        System.out.println(dynamicArrayOfIntegers.toString()); // печать внутреннего состояния (toString)
        dynamicArrayOfIntegers.remove(indexToRemove); //удаление произвольного элемента по индексу (remove)*/
        System.out.println(dynamicArrayOfIntegers.toString());
        DynamicArray<Integer> dynamicArrayOfThreeIntegers = new DynamicArray<>(3, new Integer[]{99, 100, 101}); //возможность задать начальный размер
        System.out.println(dynamicArrayOfThreeIntegers.toString());
    }
}
