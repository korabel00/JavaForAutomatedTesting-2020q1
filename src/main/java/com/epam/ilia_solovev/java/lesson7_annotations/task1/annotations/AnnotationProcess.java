package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import com.epam.ilia_solovev.java.lesson7_annotations.task1.home_appliances.HomeAppliances;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationProcess {

    public void annotationProcess() throws NoSuchFieldException {

        Class<?> ha = HomeAppliances.class;
        Annotation[] annos = ha.getAnnotations();


        for (Annotation ann : annos) {
            System.out.println(ann.annotationType().getSimpleName());
        }

        Field nameField = ha.getDeclaredField("name");
        Annotation[] fieldAnnos = nameField.getAnnotations();
        for (Annotation ann : fieldAnnos) {
            System.out.println(ann.annotationType().getSimpleName());
        }
    }
}
