package com.epam.ilia_solovev.java.lesson6_sql.task1;

import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.Connectible;
import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.DBSettings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

public class FillDB implements Connectible {

    public static void fillTables() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DBSettings.DRIVER.getValue());
            connection = Connectible.getConnection();

            preparedStatement = fillTableUsers(connection);
            preparedStatement.execute();

            preparedStatement = fillTableFriendships(connection);
            preparedStatement.execute();

            preparedStatement = fillTablePosts(connection);
            preparedStatement.execute();

            preparedStatement = fillTableLikes(connection);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException | IOException e) {
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

    private static PreparedStatement fillTableUsers(Connection connection) throws SQLException, IOException {

        //метод возвращает подготовленный запрос дл€ заполнени€ таблицы Users из файла - выполн€етс€ запрос в основном методе
        String tableName = "Users";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Users.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line;
        String cvsSplitBy = ";";

        //“олько если таблица существует и пуста€ добавл€ем в нее значени€
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Users) = 0 BEGIN\n";
        StringBuilder stringToInsert = new StringBuilder();

        bufferedReader.readLine();//skip first line with titles

        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(cvsSplitBy);
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(Name, Surname, Birthdate) VALUES ")
                    .append("('").append(columns[0]).append("', '")
                    .append(columns[1]).append("', '")
                    .append(columns[2]).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Users Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    private static PreparedStatement fillTableFriendships(Connection connection) throws SQLException, IOException {

        //метод возвращает подготовленный запрос дл€ заполнени€ таблицы Friendships из файла
        String tableName = "Friendships";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Friendships.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line;
        String cvsSplitBy = ";";

        //“олько если таблица существует и пуста€ добавл€ем в нее значени€
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Friendships) = 0 BEGIN\n";
        StringBuilder stringToInsert = new StringBuilder();
        bufferedReader.readLine();//skip first line with titles

        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(cvsSplitBy);
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(UserId, FriendsNumber, Date) VALUES ")
                    .append("('").append(columns[0]).append("', '")
                    .append(columns[1]).append("', '")
                    .append(columns[2]).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Friendships Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    private static PreparedStatement fillTablePosts(Connection connection) throws SQLException, IOException {

        //метод возвращает подготовленный запрос дл€ заполнени€ таблицы Posts из файла
        String tableName = "Posts";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Posts.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line;
        String cvsSplitBy = ";";

        //“олько если таблица существует и пуста€ добавл€ем в нее значени€
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Posts) = 0 BEGIN\n";
        StringBuilder stringToInsert = new StringBuilder();
        bufferedReader.readLine();//skip first line with titles

        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(cvsSplitBy);
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(PostId, UserId) VALUES ")
                    .append("('").append(columns[0]).append("', '")
                    .append(columns[1]).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Posts Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    private static PreparedStatement fillTableLikes(Connection connection) throws SQLException, IOException {

        //метод возвращает подготовленный запрос дл€ заполнени€ таблицы Likes из файла
        String tableName = "Likes";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Likes.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line;
        String cvsSplitBy = ";";

        //“олько если таблица существует и пуста€ добавл€ем в нее значени€
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Likes) = 0 BEGIN\n";
        StringBuilder stringToInsert = new StringBuilder();
        bufferedReader.readLine();//skip first line with titles

        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(cvsSplitBy);
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(PostId, LikesCount) VALUES ")
                    .append("('").append(columns[0]).append("', '")
                    .append(columns[1]).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Likes Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }
}