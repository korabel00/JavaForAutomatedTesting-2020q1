/*
Ilia Solovev Lesson 5 Task 2

Напишите утилиту DiskAnalyzer командной строки, которая на вход
        принимает путь (например C:\) и номер функции, корректно
        обрабатывает некорректные пути или номера функций. Утилита выводит
        в файл результаты своей работы. Программа должна работать для диска
        C: вашей рабочей машины. Поддерживаются следующие функции:
        1. Поиск имени файла с максимальным количеством букв ‘s’ в имени,
        вывод пути к нему.
        2. Top-5 файлов с самым большим размером
        3. Средний размер файла в указанной директории или любой ее
        поддиректории
        4. Количество файлов и папок разбитое по первым буквам алфавита
        (например на букву A – начинается 100 000 файлов и 200 папок)
*/

package com.epam.ilia_solovev.java.lesson5_serilize_and_files.task2_files;

public class RunDiskAnalyzer {

    public static void main(String[] args) {

        DiskAnalyzer app = new DiskAnalyzer();

        args = new String[]{"H:\\", "5"};
        app.runDiskAnalyzer(args); //Корректно обрабатывает некорректные пути или номера функций.

        args = new String[]{"C:\\Windows\\System32\\", "1"};
        app.runDiskAnalyzer(args); // Поиск имени файла с максимальным количеством букв ‘s’ в имени, вывод пути к нему.

        args = new String[]{"C:\\Windows\\System32\\", "2"};
        app.runDiskAnalyzer(args); // Top-5 файлов с самым большим размером

        args = new String[]{"C:\\Windows\\System32\\", "3"};
        app.runDiskAnalyzer(args); // Средний размер файла в указанной директории или любой ее поддиректории

        args = new String[]{"C:\\Windows\\System32\\", "4"};
        app.runDiskAnalyzer(args); // Количество файлов и папок разбитое по первым буквам алфавита

    }
}
