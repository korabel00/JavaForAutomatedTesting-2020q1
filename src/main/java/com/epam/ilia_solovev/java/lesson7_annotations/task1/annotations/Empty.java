package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Нельзя заать пустое поле

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Empty {
}
