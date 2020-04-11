package com.epam.ilia_solovev.java.lesson3_exceptions.task1.utils;

public enum Color {

    //The constants for making text colorful in console
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_RESET("\u001B[0m"),
    ANSI_BLACK("\u001B[37m"),
    ANSI_RED("\u001B[31m"),
    ANSI_BLUE("\u001B[34m");

    private String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}