package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//аннотация удостоверяется в ненулевом значении
//You cannot create an appliance with power <= 0

@Target(ElementType.FIELD) // применяется только к полю
@Retention(RetentionPolicy.RUNTIME)

public @interface ZeroPower {
}
