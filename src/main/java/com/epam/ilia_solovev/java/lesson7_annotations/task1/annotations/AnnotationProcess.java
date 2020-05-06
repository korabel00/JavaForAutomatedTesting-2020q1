package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class AnnotationProcess {

    public static void annotationProcess(Object[] homeAppliances) throws NoSuchFieldException, IllegalAccessException {

        //Получаем и обходим массив домашней электроники
        for (Object obj : homeAppliances
        ) {
            Class objClass = obj.getClass();//получаем класс нашего экземпляра
            Class superClass = obj.getClass().getSuperclass(); //получаем суперкласс нашего экземпляра

            Field[] objClassFieldsArray = objClass.getDeclaredFields(); //получаем список полей класса
            Field[] superClassFieldsArray = superClass.getDeclaredFields(); //получаем список полей суперкласса
            Field[] allFields = ArrayUtils.addAll(objClassFieldsArray, superClassFieldsArray); // все поля

            System.out.print(objClass.getSimpleName());

            for (Field field : allFields //обходим все поля экземпляра и его суперкласса
            ) {
                field.setAccessible(true);
                System.out.print(" " + field.getName() + " " + field.get(obj) + " ");

                Annotation[] annotations = field.getDeclaredAnnotations(); //получаем массив аннотаций для текущего поля
                for (Annotation ann : annotations //обходим все аннотации всех полей экземпляра и его суперкласса
                ) {
                    System.out.println(ann);
                }
                System.out.println();
            }
        /*    for (Annotation ann: annotationsArray
                 ) {
                System.out.println(ann);
            }*/
            //  System.out.println(Arrays.toString(obj.getClass().getAnnotations()));
            //  System.out.println(obj.getClass().getDeclaredField("screenSize"));
        }

        //Class<?> ha = homeAppliances.class;
        //Annotation[] annos = ha.getAnnotations();


      /*  for (Annotation ann : annos) {
            System.out.println(ann.annotationType().getSimpleName());
        }

        Field nameField = ha.getDeclaredField("name");
        Annotation[] fieldAnnos = nameField.getAnnotations();
        for (Annotation ann : fieldAnnos) {
            System.out.println(ann.annotationType().getSimpleName());
        }*/
    }
}
