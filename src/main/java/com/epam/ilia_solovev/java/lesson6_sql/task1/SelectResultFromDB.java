package com.epam.ilia_solovev.java.lesson6_sql.task1;

import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.Connectible;
import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.DBSettings;

import java.sql.*;

public class SelectResultFromDB implements Connectible {

    public static void executeSelect() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DBSettings.DRIVER.getValue());
            connection = Connectible.getConnection();

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

    private static PreparedStatement sentSelect(Connection connection) throws SQLException {

        //посылаем возвращаем подготовленный SELECT, исполним дальше - в основном методе
        System.out.println("Sending SELECT to the DB " + DBSettings.DB_NAME.getValue() + " Database...");
        String stringToPrepare = "IF DB_ID('" + DBSettings.DB_NAME.getValue() + "') IS NOT NULL " +
                "USE " + DBSettings.DB_NAME.getValue() + "\n" +
                "SELECT ID, Name, Surname, Birthdate, FriendsNumber, Date, Likes.PostId, LikesCount FROM Users\n" +
                "JOIN Friendships ON Friendships.UserId = Users.ID\n" +
                "JOIN Posts ON Posts.UserId = Users.ID\n" +
                "JOIN Likes ON Likes.PostId = Posts.PostId\n" +
                "WHERE Friendships.date BETWEEN '2015-03-01' AND '2015-03-31' AND FriendsNumber >= 3 AND LikesCount >= (SELECT AVG(LikesCount) FROM Likes);";

        System.out.println(stringToPrepare);
        return connection.prepareStatement(stringToPrepare);
    }

    private static void printResultOfSelect (ResultSet resultSet) throws SQLException {

        //выводим результаты SELECT
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnsNumber = resultSetMetaData.getColumnCount();
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(resultSetMetaData.getColumnName(i) + ": " + columnValue);
            }
            System.out.println();
        }
    }
}
