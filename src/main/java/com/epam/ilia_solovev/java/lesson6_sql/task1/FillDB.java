package com.epam.ilia_solovev.java.lesson6_sql.task1;

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

public class FillDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433";
    private static final String INSTANCE = "instance=SQLEXPRESS";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_NAME = "VEpamke";
    private static final String CREDENTIALS = "integratedSecurity=true";

    public static void fillTables() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DRIVER);
            connection = getConnection();

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

    private static Connection getConnection() throws SQLException {
        System.out.println("Connecting to " + URL + ";" + INSTANCE + ";" + CREDENTIALS + "...");
        return DriverManager.getConnection(URL + ";" + INSTANCE + ";" + CREDENTIALS);
    }

    private static PreparedStatement fillTableUsers(Connection connection) throws SQLException, IOException {

        String tableName = "Users";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Users.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line = "";
        String cvsSplitBy = ";";
        String stringToPrepare = "USE " + DB_NAME + "; " +
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

        String tableName = "Friendships";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Friendships.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line = "";
        String cvsSplitBy = ";";
        String stringToPrepare = "USE " + DB_NAME + "; " +
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

        String tableName = "Posts";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Posts.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line = "";
        String cvsSplitBy = ";";
        String stringToPrepare = "USE " + DB_NAME + "; " +
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

        String tableName = "Likes";
        String csvFile = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Likes.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String line = "";
        String cvsSplitBy = ";";
        String stringToPrepare = "USE " + DB_NAME + "; " +
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