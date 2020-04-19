/*      Solovev Ilia Lesson 6 Task 1

        Создайте простое консольное приложение для работы с базой данных MySQL,
        развернутой локально на вашей машине. В базе данных VEpamke вы должны
        создать таблицы Users (id, name, surname, birthdate), Friendships (userId, friendsnumber,
        data), Posts(id, userId), Likes (postid, likes).
        Заполните таблицы осмысленными данными. Кроме этого, вы должны написать класс, который при наличии
        данных в вашей БД печатает список уникальных имен пользователей, у которых
        было больше 3 друзей в марте 2015 года и количество лайков каждого
        поста (за весь период) выше среднего. Отчет выводится на консоль. Все
        действия должны быть воплощены при помощи JDBC.*/

package com.epam.ilia_solovev.java.lesson6_sql.task1;

public class RunSQLHandler {

    public static void main(String[] args) {

        CreateDB.createDBAndTables();
        FillDB.fillTables();
        SelectResultFromDB.executeSelect();
    }
}  
