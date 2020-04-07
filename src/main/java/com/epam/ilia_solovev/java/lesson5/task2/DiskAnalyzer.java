


package com.epam.ilia_solovev.java.lesson5.task2;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DiskAnalyzer {

    public void runDiskAnalyzer(String[] args) {

        String letterToFind = "s";

        checkInput(args);
        switch (args[1]) {
            case "1":
                findMaxNumberOfLetterinFileName(letterToFind);
                break;
            case "2":
                findTopFiveSizeFilesInDirectory();
                break;
            case "3":
                countAvarageFileSizeInDirectory();
                break;
            case "4":
                countFilesAndDirsByStaringLetter();
                break;
        }
    }

    private void countFilesAndDirsByStaringLetter() {
        System.out.println("countFilesAndDirsByStaringLetter method");
    }

    private void countAvarageFileSizeInDirectory() {
        System.out.println("countAvarageFileSizeInDirectory method");
    }

    private void findTopFiveSizeFilesInDirectory() {
        System.out.println("findTopFiveSizeFilesInDirectory method");
    }

    private void findMaxNumberOfLetterinFileName(String letterToFind) {
        System.out.println("findMaxNumberOfLetterinFileName method");
    }


    private void checkInput(String[] args) {

        int functionNumber = Integer.parseInt(args[1]);

        try {
            if (args.length != 2) {
                throw new Exception("Wrong number of arguments");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            if (functionNumber < 1 || functionNumber > 4) {
                throw new Exception("Wrong function number");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            if (!Files.exists(Paths.get(args[0]))) {
                throw new Exception("Wrong path");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
