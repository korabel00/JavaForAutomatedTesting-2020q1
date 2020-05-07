package com.epam.ilia_solovev.java.lesson6_sql.task1;

import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.Connectible;
import com.epam.ilia_solovev.java.lesson6_sql.task1.utils.DBSettings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class FillDB implements Connectible {

    static int userCollectionLength;
    static int postsCollectionLength;

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

        //����� ���������� �������������� ������ ��� ���������� ������� Users �� ����� - ����������� ������ � �������� ������
        // Users (ID, Name, Surname, Birthdate)
        String tableName = "Users";
        String fileWithNamesPath = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Names.csv";
        String fileWithSurnamesPath = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Surnames.csv";

        //������� 2 ��������� � ������� � ��������� �� ������ � ������������ ��
        ArrayList<String> namesCollection = readFileAndShuffle(fileWithNamesPath);
        ArrayList<String> surnamesCollection = readFileAndShuffle(fileWithSurnamesPath);
        StringBuilder stringToInsert = new StringBuilder();

        //������ ���� ������� ���������� � ������ ��������� � ��� ��������
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Users) = 0 BEGIN\n";

        //���������� � ������� ����� � ������� �� ��������� + ���������� ���� �������� ��������� �������
        userCollectionLength = namesCollection.size();
        for (int i = 0; i < userCollectionLength; i++) {
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(Name, Surname, Birthdate) VALUES ")
                    .append("('").append(namesCollection.get(i)).append("', '")
                    .append(surnamesCollection.get(i)).append("', '")
                    .append(randomDateOfBirth()).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Users Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    private static PreparedStatement fillTableFriendships(Connection connection) throws SQLException, IOException {

        //����� ���������� �������������� ������ ��� ���������� ������� Friendships �� �����
        //Friendships (UserId1, UserId2, Timestamp)
        String tableName = "Friendships";

        //������ ���� ������� ���������� � ������ ��������� � ��� ��������
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Friendships) = 0 BEGIN\n";
        StringBuilder stringToInsert = new StringBuilder();

        //���������� � ������� ��������� ������� "������" � ������ UserId
        for (int i = 0; i < userCollectionLength; i++) {
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(UserId2, Timestamp) VALUES ")
                    .append("('").append(randBetween(1, userCollectionLength)).append("', '")
                    .append(randomTimestamp()).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Friendships Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    private static PreparedStatement fillTablePosts(Connection connection) throws SQLException, IOException {

        //����� ���������� �������������� ������ ��� ���������� ������� Posts �� �����
        //Posts (UserId, Text, Timestamp)
        String tableName = "Posts";
        String fileWithPostsPath = "src\\main\\java\\com\\epam\\ilia_solovev\\java\\lesson6_sql\\task1\\data\\Posts.csv";

        //������� 2 ��������� � ������� � ��������� �� ������ � ������������ ��
        ArrayList<String> postsCollection = readFileAndShuffle(fileWithPostsPath);
        StringBuilder stringToInsert = new StringBuilder();

        //������ ���� ������� ���������� � ������ ��������� � ��� ��������
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Posts) = 0 BEGIN\n";

        //���������� � ������� ����� � ������� �� ��������� + ���������� ���� �������� ��������� �������
        postsCollectionLength = postsCollection.size();
        for (int i = 0; i < postsCollectionLength; i++) {
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(UserId, Text, Timestamp) VALUES ")
                    .append("('").append(randBetween(1, 20)).append("', '")
                    .append(postsCollection.get(i)).append("', '")
                    .append(randomTimestamp()).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Posts Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    private static PreparedStatement fillTableLikes(Connection connection) throws SQLException, IOException {

        //����� ���������� �������������� ������ ��� ���������� ������� Likes �� �����
        //PostId int, UserId int, Timestamp datetime
        String tableName = "Likes";
        int LikesNumber = 100;

        //������ ���� ������� ���������� � ������ ��������� � ��� ��������
        String stringToPrepare = "USE " + DBSettings.DB_NAME.getValue() + "; " +
                "IF OBJECT_ID('" + tableName + "') IS NOT NULL AND (SELECT COUNT(*) FROM Likes) = 0 BEGIN\n";
        StringBuilder stringToInsert = new StringBuilder();

        for (int i = 0; i < LikesNumber; i++) {
            stringToInsert.append("INSERT INTO ")
                    .append(tableName)
                    .append("(PostId, UserId, Timestamp) VALUES ")
                    .append("('").append(randBetween(1, postsCollectionLength)).append("', '")
                    .append(randBetween(1, userCollectionLength)).append("', '")
                    .append(randomTimestamp()).append("'); \n");
        }
        stringToInsert.append("END\n");
        System.out.println("Put data into Likes Table...");
        System.out.println(stringToPrepare + stringToInsert.toString());
        return connection.prepareStatement(stringToPrepare + stringToInsert.toString());
    }

    public static ArrayList<String> readFileAndShuffle(String filePath) throws IOException {

        Path path = Paths.get(filePath);
        ArrayList<String> fileCollection = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(path);
        String line;

        while ((line = reader.readLine()) != null) {
            fileCollection.add(line);
        }
        Collections.shuffle(fileCollection);
        return fileCollection;
    }

    public static String randomDateOfBirth() {

        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1960, 2000);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH);
    }

    public static String randomTimestamp() {

        long start = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2020-01-01 00:00:00").getTime();
        long diff = end - start + 1;
        Timestamp rand = new Timestamp(start + (long)(Math.random() * diff));

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").format(rand);
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}