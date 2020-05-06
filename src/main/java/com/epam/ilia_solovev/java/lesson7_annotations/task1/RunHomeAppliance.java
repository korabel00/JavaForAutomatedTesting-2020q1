/*
 * Ilia Solovev. Lesson7 Task 1
 *
 * Создайте несколько аннотаций для вашей иерархии классов из 2 ДЗ. Например,
 * аннотации, проверяющие диапазоны значений типов, ненулевые значения, запрет
 * на использование каких-либо значений и т.д по желанию ментора.
 * Напишите свой процессор аннотаций, основанный на Reflection API, который парсит
 * классы и «применяет» аннотации к экземплярам классов из иерархии (2 ДЗ).
 * Предоставьте тестовый код, где инициализируются экземпляры классов из ирерахии (2 ДЗ)
 * и запускается процессор аннтоаций, выдающий warnings на консоль, если поле
 * проинициализировано запрещенным значением, например (и вообще во всех
 * случаях, когда содержимое private-полей нарушает ограничения заданные в аннотациях).
 *
 */

package com.epam.ilia_solovev.java.lesson7_annotations.task1;

import com.epam.ilia_solovev.java.lesson7_annotations.task1.home_appliances.*;
import com.epam.ilia_solovev.java.lesson7_annotations.task1.utils.Brand;

public class RunHomeAppliance {

    public static void main(String[] args) {
        RunHomeAppliance app = new RunHomeAppliance();
        app.startApp();
    }

    //Running an application
    public void startApp() {

        // создаем массив домашней электроники
        HomeAppliances[] homeAppliances = new HomeAppliances[3];
        createArrayOfHomeAppliance(homeAppliances);

        // аннотация @CanItWork не позволяет работать вещи, которая выключена
        homeAppliances[2].turnOff();
        homeAppliances[2].doWork();
    }

    private void createArrayOfHomeAppliance(HomeAppliances[] homeAppliances) {

        //аннотация @ZeroPower не позволяет создавать электронику с мощностью меньше или равную нулю
        WashingMachine washingMachine = new WashingMachine(0, Brand.Indesit, "IWUB 4085", 1000);

        //аннотация @RangeCheck позволяет создавать телевизор только с диагональю от 25 до 85  дюймов
        TV tv = new TV(200, Brand.Samsung, "UE50NU7097U", 10);

        //аннотация @EmptyModel не позволяет создавать электронику с пустым значением модели
        Computer computer = new Computer(400, Brand.DELL, "");

        homeAppliances[0] = tv;
        homeAppliances[1] = washingMachine;
        homeAppliances[2] = computer;
    }

}
