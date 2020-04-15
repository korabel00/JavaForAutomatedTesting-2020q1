/*      Solovev Ilia Lesson 6 Task 1

        Создайте простое консольное приложение для работы с базой данных MySQL,
        развернутой локально на вашей машине. В базе данных VEpamke вы должны
        создать таблицы Users (id, name, surname, birthdate), Friendships (userid1, userid2,
        timestamp), Posts(id, userId, text, timestamp), Likes (postid, userid, timestamp).
        Заполните таблицы осмысленными данными (> 1 000 users, > 70 000 friendships, >
        300 000 likesin 2015). Кроме этого, вы должны написать класс, который при наличии
        данных в вашей БД печатает список уникальных имен пользователей, у которых
        было больше 100 друзей в марте 2015 года и среднее количество лайков каждого
        поста (за весь период) лежит в диапазоне [3; 15). Отчет выводится на консоль. Все
        действия должны быть воплощены при помощи JDBC.*/

package com.epam.ilia_solovev.java.lesson6_sql.task1;

public class RunSQLHandler {

    public static void main(String[] args) {

        CreateDB.createDBAndTables();
    }
}  
