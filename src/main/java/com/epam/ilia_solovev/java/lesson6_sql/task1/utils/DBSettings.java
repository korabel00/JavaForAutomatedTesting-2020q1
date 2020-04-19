package com.epam.ilia_solovev.java.lesson6_sql.task1.utils;

public enum DBSettings {
    URL("jdbc:sqlserver://localhost:1433"),
    INSTANCE("instance=SQLEXPRESS"),
    DRIVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    DB_NAME("VEpamke"),
    CREDENTIALS("integratedSecurity=true");
    private String value;

    public String getValue() {
        return value;
    }

    DBSettings(String value) {
        this.value = value;
    }
}