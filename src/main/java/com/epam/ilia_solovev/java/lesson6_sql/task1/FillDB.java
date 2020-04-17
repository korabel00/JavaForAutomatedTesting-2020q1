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

            //        preparedStatement = fillTableFriendships(connection);
            //        preparedStatement.execute();

            //        preparedStatement = fillTablePosts(connection);
            //        preparedStatement.execute();

            //        preparedStatement = fillTableLikes(connection);
            //        preparedStatement.execute();

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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        System.out.println(URL + ";" + INSTANCE + ";" + CREDENTIALS);
        return DriverManager.getConnection(URL + ";" + INSTANCE + ";" + CREDENTIALS);
    }


    // Users (id, name, surname, birthdate)
    private static PreparedStatement fillTableUsers(Connection connection) throws SQLException, IOException {

        String tableName = "Users";
        Path csvFile = Paths.get("src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\date\\Users.csv");
        BufferedReader bufferedReader = null;
        String line = "";
        String cvsSplitBy = ";";
        String stringToPrepare = "USE " + DB_NAME + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL ";
        StringBuilder stringToInsert = null;
        try (Stream<String> stream = Files.lines(csvFile)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*

        while ((line = Files.readString(csvFile) != null) {
            String[] lineReader = line.split(cvsSplitBy);
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(Name, Surname, Birthdate) VALUES ")
                    .append("('").append(lineReader[0]).append("', '")
                                 .append(lineReader[1]).append("', '")
                                 .append(lineReader[2]).append("');");
        }
*/

        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    // Friendships (userid1, userid2,timestamp)
    private static PreparedStatement fillTableFriendships(Connection connection) throws SQLException {

        String tableName = "Friendships";

        return connection.prepareStatement("USE " + DB_NAME + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NULL " +
                "CREATE TABLE " + tableName + "(" +
                "UserId1 int, " +
                "UserId2 int, " +
                "Timestamp datetime, " +
                "FOREIGN KEY(UserId1) REFERENCES Users (ID));");
    }

    // Posts(id, userId, text, timestamp)
    private static PreparedStatement fillTablePosts(Connection connection) throws SQLException {

        String tableName = "Posts";

        return connection.prepareStatement("USE " + DB_NAME + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NULL " +
                "CREATE TABLE " + tableName + "(" +
                "ID int PRIMARY KEY, " +
                "UserId int, " +
                "Text varchar(255), " +
                "Timestamp datetime, " +
                "FOREIGN KEY(UserId) REFERENCES Users (ID));");
    }

    // Likes (postid, userid, timestamp)
    private static PreparedStatement fillTableLikes(Connection connection) throws SQLException {

        String tableName = "Likes";

        return connection.prepareStatement("USE " + DB_NAME + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NULL " +
                "CREATE TABLE " + tableName + "(" +
                "PostId int PRIMARY KEY, " +
                "UserId int, " +
                "Timestamp datetime, " +
                "FOREIGN KEY(UserId) REFERENCES Users (ID));");
    }
}
