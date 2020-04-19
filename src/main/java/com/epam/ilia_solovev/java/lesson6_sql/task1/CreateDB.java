package com.epam.ilia_solovev.java.lesson6_sql.task1;

import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.Connectible;
import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.DBSettings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDB implements Connectible {

    public static void createDBAndTables() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // каждый раз при взаимодействии с базой устанавливаем соединение, создаем и исполняем подготовленный запрос
        // а потом закрываем подготовленный запрос и соединение
        try {
            Class.forName(DBSettings.DRIVER.getValue());
            connection = Connectible.getConnection();

            preparedStatement = createDB(connection);
            preparedStatement.execute();

            preparedStatement = createTableUsers(connection);
            preparedStatement.execute();

            preparedStatement = createTableFriendships(connection);
            preparedStatement.execute();

            preparedStatement = createTablePosts(connection);
            preparedStatement.execute();

            preparedStatement = createTableLikes(connection);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Close connection...");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static PreparedStatement createDB(Connection connection) throws SQLException {

        //метод возвращает подготовленный запрос для создания базы данных
        System.out.println("Create " + DBSettings.DB_NAME.getValue() + " Database...");
        return connection.prepareStatement("IF DB_ID('" + DBSettings.DB_NAME.getValue() + "') IS NULL " +
                "CREATE DATABASE " + DBSettings.DB_NAME.getValue());
    }

    private static PreparedStatement createTableUsers(Connection connection) throws SQLException {

        //метод возвращает подготовленный запрос для создания таблицы Users - выполняется запрос в основнм методе
        String tableName = "Users";

        System.out.println("Create Users Table...");
        return connection.prepareStatement("USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NULL " +
                "CREATE TABLE " + tableName + "(" +
                "ID int IDENTITY(1,1) PRIMARY KEY, " +
                "Name varchar(20), " +
                "Surname varchar(30), " +
                "Birthdate date);");
    }

    private static PreparedStatement createTableFriendships(Connection connection) throws SQLException {

        //метод возвращает подготовленный запрос для создания таблицы Friendships
        String tableName = "Friendships";

        System.out.println("Create Friendships Table...");
        return connection.prepareStatement("USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NULL " +
                "CREATE TABLE " + tableName + "(" +
                "UserId int, " +
                "FriendsNumber int, " +
                "Date date, " +
                "FOREIGN KEY(UserId) REFERENCES Users (ID));");
    }

    private static PreparedStatement createTablePosts(Connection connection) throws SQLException {

        //метод возвращает подготовленный запрос для создания таблицы Posts
        String tableName = "Posts";

        System.out.println("Create Posts Table...");
        return connection.prepareStatement("USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NULL " +
                "CREATE TABLE " + tableName + "(" +
                "PostId int PRIMARY KEY, " +
                "UserId int, " +
                "FOREIGN KEY(UserId) REFERENCES Users (ID));");
    }

    private static PreparedStatement createTableLikes(Connection connection) throws SQLException {

        //метод возвращает подготовленный запрос для создания таблицы Likes
        String tableName = "Likes";

        System.out.println("Create Likes Table...");
        return connection.prepareStatement("USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NULL " +
                "CREATE TABLE " + tableName + "(" +
                "PostId int PRIMARY KEY, " +
                "LikesCount int, " +
                "FOREIGN KEY(PostId) REFERENCES Posts (PostId));");
    }
}


