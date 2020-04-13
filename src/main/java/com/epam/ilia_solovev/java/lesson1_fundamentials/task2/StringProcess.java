/*
 * Ilia Solovev. Task 2-1
 *
 * Ввести n строк с консоли, найти самую короткую и самую длинную
 * строки. Вывести найденные строки и их длину.
 */

package com.epam.ilia_solovev.java.lesson1_fundamentials.task2;

import java.util.Scanner;

public class StringProcess {

    private int n = 6;
    private String[] userString = new String[n];
    private String theShortestString;
    private String theLongestString;

    public static void main(String[] args) {
        StringProcess app = new StringProcess();
        app.startStringProcess();
    }

    //Running an application
    public void startStringProcess() {
        getUserInput();
        findTheShortestAndTheLongestStrings();
        outputResult();
    }

    //getting user input - exactly n strings
    private void getUserInput() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter " + n + " strings:");

        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + " string: ");  // Output user input
            userString[i] = scanner.nextLine();  // Read user input
        }
    }

    //finding the shortest and the longest ones
    private void findTheShortestAndTheLongestStrings() {

        theLongestString = userString[0];
        theShortestString = userString[0];

        for (int i = 1; i < n; i++) {
            if (userString[i].length() > theLongestString.length()) {
                theLongestString = userString[i];
            }
            if (userString[i].length() < theShortestString.length()) {
                theShortestString = userString[i];
            }
        }
    }

    //output the shortest and the longest string of user input
    private void outputResult() {

        System.out.println("The Shortest string is: \"" + theShortestString + "\" and its length is " +
                theShortestString.length());
        System.out.println("The Longest string is: \"" + theLongestString + "\" and its length is " +
                theLongestString.length());
    }
}



