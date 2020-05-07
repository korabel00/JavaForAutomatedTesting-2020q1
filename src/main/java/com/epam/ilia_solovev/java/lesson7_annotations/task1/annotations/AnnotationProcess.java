package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import com.epam.ilia_solovev.java.lesson7_annotations.task1.utils.Color;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationProcess {

    public static void annotationProcess(Object[] homeAppliances) throws IllegalAccessException, InvocationTargetException {


        //Получаем и обходим массив домашней электроники
        for (Object obj : homeAppliances
        ) {
            Class objClass = obj.getClass();//получаем класс нашего экземпляра
            Class superClass = obj.getClass().getSuperclass(); //получаем суперкласс нашего экземпляра

            Field[] objClassFieldsArray = objClass.getDeclaredFields(); //получаем список полей класса
            Field[] superClassFieldsArray = superClass.getDeclaredFields(); //получаем список полей суперкласса
            Field[] allFields = ArrayUtils.addAll(objClassFieldsArray, superClassFieldsArray); // все поля
            fieldsAnnotationProcess(allFields, obj); //проверяем поля на аннотации

            Method[] objClassMethodsArray = objClass.getDeclaredMethods(); //получаем список методов класса
            Method[] superClassMethodsArray = superClass.getDeclaredMethods(); //получаем список методов суперкласса
            Method[] allMethods = ArrayUtils.addAll(objClassMethodsArray, superClassMethodsArray); // все методы
            methodsAnnotationProcess(allMethods, obj); //проверяем класс на наличие метода
        }
    }

    private static void fieldsAnnotationProcess(Field[] allFields, Object obj) throws IllegalAccessException {
        for (Field field : allFields //обходим все поля экземпляра и его суперкласса
        ) {
            field.setAccessible(true);

            Annotation[] annotations = field.getDeclaredAnnotations(); //получаем массив аннотаций для текущего поля
            for (Annotation ann : annotations //обходим все аннотации всех полей экземпляра и его суперкласса
            ) {
                //если для поля установлена Аннотация @Zero то проверяем что оно не должно быть <= 0
                if (ann.annotationType().equals(Zero.class) && (int) field.get(obj) <= 0) {
                    System.out.println(Color.ANSI_RED.getCode() + "You cannot set " + field.get(obj) + " to field "
                            + field.getName() + Color.ANSI_RESET.getCode());
                }
                //если для поля установлена Аннотация @RangeCheck то проверяем диапазон 25-85
                if (ann.annotationType().equals(RangeCheck.class) && ((int) field.get(obj) < 25 ||
                        (int) field.get(obj) > 85)) {
                    System.out.println(Color.ANSI_RED.getCode() + "You cannot set " + field.get(obj) + " to field "
                            + field.getName() + Color.ANSI_RESET.getCode());
                }
                //если для поля установлена Аннотация @RangeCheck то проверяем диапазон 25-85
                if (ann.annotationType().equals(Empty.class) && field.get(obj).equals("")) {
                    System.out.println(Color.ANSI_RED.getCode() + "You cannot left empty field " +
                            field.getName() + Color.ANSI_RESET.getCode());
                }
            }
        }
    }

    private static void methodsAnnotationProcess(Method[] allMethods, Object obj) throws IllegalAccessException, InvocationTargetException {

        Annotation[] annotations = obj.getClass().getDeclaredAnnotations(); //получаем массив аннотаций для текущего экземпляра
        for (Annotation ann : annotations //обходим все аннотации экземпляра
        ) {
            //если для класса установлена Аннотация @NoWiFi то проверяем есть ли метод connectToWiFi() у класса
            if (ann.annotationType().equals(NoWiFi.class)) {
                boolean noWiFi = false;
                for (Method method : allMethods //обходим все методы экземпляра и его суперкласса
                ) {
                    method.setAccessible(true);
                    if (method.getName().equals("connectToWiFi")) {
                        noWiFi = true;
                    }
                }
                if (!noWiFi) {
                    System.out.println(Color.ANSI_RED.getCode() + "Home appliance " + obj.getClass().getSimpleName() +
                            " has no WiFI" + Color.ANSI_RESET.getCode());
                }
            }
        }


    }
}


