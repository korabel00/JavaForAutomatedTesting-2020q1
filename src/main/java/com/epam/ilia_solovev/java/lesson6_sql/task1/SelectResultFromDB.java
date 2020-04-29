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
                "SELECT DISTINCT A.Fullname FROM\n" +
                "(SELECT u.Name + ' ' + u.Surname AS Fullname, COUNT(l.UserId) AS Likes, " +
                "COUNT(l.userid) / COUNT(DISTINCT l.postid) AS AvgLikes FROM Users AS u\n" +
                "INNER JOIN (select UserId2 FROM Friendships as fr where fr.timestamp > '2015-03-01 00:00:01'\n" +
                "GROUP BY UserId2\n" +
                "HAVING COUNT(fr.userid2) > 1) AS frnd\n" +
                "ON frnd.userid2 = u.Id\n" +
                "INNER JOIN Posts as p\n" +
                "ON p.userID = u.Id\n" +
                "INNER JOIN Likes as l\n" +
                "ON l.postid = p.id\n" +
                "GROUP BY u.name, u.surname, u.Birthdate,l.PostId) AS A\n" +
                "GROUP BY A.Likes, A.Fullname, AvgLikes\n" +
                "HAVING COUNT(A.Likes) > AvgLikes";
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
