package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//TV screen size suppose to be from 25 to 85 inches

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface ScreenSizeRangeCheck {
}
