package com.epam.ilia_solovev.java.lesson6_sql.task1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Connectible {

    static Connection getConnection() throws SQLException {

        //метод возвращает подготовленный запрос для соединения с сервером базы данных
        System.out.println("Connecting to " + DBSettings.URL.getValue() + ";" + DBSettings.INSTANCE.getValue() + ";"
                + DBSettings.CREDENTIALS.getValue() + "...");
        return DriverManager.getConnection(DBSettings.URL.getValue() + ";" + DBSettings.INSTANCE.getValue() + ";"
                + DBSettings.CREDENTIALS.getValue());
    }
}
