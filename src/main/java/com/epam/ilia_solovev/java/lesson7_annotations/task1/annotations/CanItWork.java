package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Аннотация 3 не позволяет работать вещи, которая выключена

@Target(ElementType.FIELD) // применяется только к полю
@Retention(RetentionPolicy.RUNTIME)

public @interface CanItWork {

}
