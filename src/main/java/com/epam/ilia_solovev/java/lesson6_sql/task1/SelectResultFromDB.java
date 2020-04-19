package com.epam.ilia_solovev.java.lesson6_sql.task1;

import java.sql.*;

public class SelectResultFromDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433";
    private static final String INSTANCE = "instance=SQLEXPRESS";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_NAME = "VEpamke";
    private static final String CREDENTIALS = "integratedSecurity=true";

    public static void executeSelect() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DRIVER);
            connection = getConnection();

            preparedStatement = sentSelect(connection);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            printResultOfSelect(resultSet);

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

    private static Connection getConnection() throws SQLException {
        System.out.println("Connecting to " + URL + ";" + INSTANCE + ";" + CREDENTIALS + "...");
        return DriverManager.getConnection(URL + ";" + INSTANCE + ";" + CREDENTIALS);
    }


    private static PreparedStatement sentSelect(Connection connection) throws SQLException {

        System.out.println("Sending SELECT to the DB " + DB_NAME + " Database...");
        String stringToPrepare = "IF DB_ID('" + DB_NAME + "') IS NOT NULL " +
                "USE " + DB_NAME + "\n" +
                "SELECT ID, Name, Surname, Birthdate, FriendsNumber, Date, Likes.PostId, LikesCount FROM Users\n" +
                "JOIN Friendships ON Friendships.UserId = Users.ID\n" +
                "JOIN Posts ON Posts.UserId = Users.ID\n" +
                "JOIN Likes ON Likes.PostId = Posts.PostId\n" +
                "WHERE Friendships.date BETWEEN '2015-03-01' AND '2015-03-31' AND FriendsNumber >= 3 AND LikesCount >= (SELECT AVG(LikesCount) FROM Likes);";

        System.out.println(stringToPrepare);
        return connection.prepareStatement(stringToPrepare);
    }

    private static void printResultOfSelect (ResultSet resultSet) throws SQLException {

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnsNumber = resultSetMetaData.getColumnCount();
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(resultSetMetaData.getColumnName(i) + ": " + columnValue);
            }
            System.out.println("");
        }
    }
}
