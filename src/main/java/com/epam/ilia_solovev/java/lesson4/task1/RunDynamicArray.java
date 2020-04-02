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

package com.epam.ilia_solovev.java.lesson4.task1;

public class RunDynamicArray {

    public static void main(String[] args) {

        RunDynamicArray app = new RunDynamicArray();
        app.startApp();
    }

    public void startApp() {

        int indexToGet = 3;
        int indexToRemove = 10;

        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.add("Last String"); //массив, поддерживающий добавление элемента в конец массива (add),
        System.out.println(dynamicArray.getElement(indexToGet));//получение элемента по индексу (get),
        dynamicArray.toString(); // печать внутреннего состояния (toString)
        dynamicArray.remove(indexToRemove); //удаление произвольного элемента по индексу (remove)
        DynamicArray dynamicArray100 = new DynamicArray(100); //возможность задать начальный размер


    }
}
