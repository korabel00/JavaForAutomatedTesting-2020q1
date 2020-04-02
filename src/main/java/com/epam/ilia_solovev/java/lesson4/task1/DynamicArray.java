package com.epam.ilia_solovev.java.lesson4.task1;

public class DynamicArray {

    private int size = 3;
    private String[] array;

    public DynamicArray () {
        array = new String[size];
        System.out.println(array.length);
    }

    public DynamicArray (int size) {
        this.size = size;
        array = new String[size];
        System.out.println(array.length);
    }

    public void add(String lastString) {
        size ++;
        array = new String[size];
        System.out.println(array.length);
        array[size - 1] = lastString;
    }

    public String getElement(int indexToGet) {
        return array[indexToGet];
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return null;
    }

    public void remove(int indexToRemove) {

    }
}
