package com.epam.ilia_solovev.java.lesson4_streams_and_collections.task1_generics;

public class DynamicArray<E> {

    private int size = 0;
    private Object[] array;

    public DynamicArray () {
        array = new Object[size];
    }

    public DynamicArray (int size, E[] e) {
        this.size = size;
        array = e;
    }

    public void add(E e) {

        //если длина массива всего 1 - не копируем массив, а просто добавляем элемент
        if (size == 0) {
            size++;
            array = new Object[size];
            array[0] = e;
         //в противном случае копируем и добавляем элемент
        } else {
            Object[] tempArrayForCopy = array;
            size ++;
            array = new Object[size];
            System.arraycopy(tempArrayForCopy, 0, array, 0, tempArrayForCopy.length);
            array[size - 1] = e;
        }
    }

    public void remove(int indexToRemove) {

        Object[] tempArrayForCopy = array;
        size--;
        array = new Object[size];
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if (i == indexToRemove) {
                count = 1;
            }
            array[i] = tempArrayForCopy[i + count];
        }
    }

    public E getElement(int indexToGet) {
        return (E) array[indexToGet];
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : array) {
            stringBuilder.append(o).append(", ");
        }
        return stringBuilder.toString();
    }
}
