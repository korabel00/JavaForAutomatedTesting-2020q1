package com.epam.ilia_solovev.java.lesson5.task2;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DiskAnalyzer {

    public void runDiskAnalyzer(String[] args) {

        String letterToFind = "s";

        checkInput(args);

        switch (args[1]) {
            case "1":
                findMaxNumberOfLettersInFileName(args, letterToFind);
                break;
            case "2":
                findTopFiveSizeFilesInDirectory(args);
                break;
            case "3":
                countAverageFileSizeInDirectory(args);
                break;
            case "4":
                countFilesAndDirsByStaringLetter(args);
                break;
        }
    }

    private ArrayList<String> getListOfFilesInDirAndSubDir(File dir, ArrayList<String> fileNamesInStrings) {

        //рекурсивное получение списка файлов в каталоге и подкаталогах
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        getListOfFilesInDirAndSubDir(file, fileNamesInStrings);
                    } else {
                        fileNamesInStrings.add(file.getName());
                    }
                }
            }
        }
        return fileNamesInStrings;
    }

    private ArrayList<String> retrieveDirJava7(File dir, ArrayList<String> fileNamesInStrings) {

        //Получение списка файлов способом из Java7
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        getListOfFilesInDirAndSubDir(file, fileNamesInStrings);
                    } else {
                        fileNamesInStrings.add(file.getName());
                    }
                }
            }
        }
        return fileNamesInStrings;
    }


    private void findMaxNumberOfLettersInFileName(String[] args, String letterToFind) {

        ArrayList<String> fileNamesInStrings = new ArrayList<>();
        //вызываем метод рекурсивного сбора списка файлов
        ArrayList<String> listOfFileNames = getListOfFilesInDirAndSubDir(new File(args[0]), fileNamesInStrings);
        // System.out.println(listOfFileNames.size());
        int countLetter = 0;
        String fileWithMaxNumberOfLetters = null;

        //считаем количество вхождений предоставленной буквы в имени файла
        for (String filename : listOfFileNames
        ) {
            if (StringUtils.countMatches(filename, letterToFind) > countLetter) {
                countLetter = StringUtils.countMatches(filename, letterToFind);
                fileWithMaxNumberOfLetters = filename;
            }
        }
        System.out.println("The file with maximum '" + letterToFind + "' letter occurrence in " + args[0] + " is: "
                + fileWithMaxNumberOfLetters);
    }

    private void findTopFiveSizeFilesInDirectory(String[] args) {
        // retrieveDir(new File(args[0]));
        System.out.println("findTopFiveSizeFilesInDirectory method...");
       /* try (Stream<Path> paths = Files.walk(Paths.get(args[0]))) {
            paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void countAverageFileSizeInDirectory(String[] args) {
        System.out.println("countAverageFileSizeInDirectory method...");
        File files = new File(args[0]);
    }

    private void countFilesAndDirsByStaringLetter(String[] args) {
        System.out.println("countFilesAndDirsByStaringLetter method...");
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

        try {
            if (!Files.isDirectory(Paths.get(args[0]))) {
                throw new Exception("Not a directory");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
