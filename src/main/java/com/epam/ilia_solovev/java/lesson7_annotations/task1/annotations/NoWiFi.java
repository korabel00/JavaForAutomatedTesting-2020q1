package com.epam.ilia_solovev.java.lesson7_annotations.task1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// аннотация @NoWifi возвращает название класса у которого нету возможности соединения с WiFi

@Target(ElementType.TYPE) // применяется только к методу
@Retention(RetentionPolicy.RUNTIME)

public @interface NoWiFi {

}
