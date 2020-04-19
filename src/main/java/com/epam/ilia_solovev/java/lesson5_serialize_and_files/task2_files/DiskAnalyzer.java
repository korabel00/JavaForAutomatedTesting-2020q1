package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task2_files;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiskAnalyzer {

    private Path fileToWrite = Paths.get("src\\main\\java\\com\\epam\\ilia_solovev\\java\\" +
            "lesson5_serialize_and_files\\task2_files\\DiskAnalyzerOutput.txt");

    public void runDiskAnalyzer(String[] args) throws IOException {

        String letterToFind = "s";

        checkInput(args);
        //во всех методах выводим результат на консоль и пишем в файл
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
                countFilesAndDirsByFirstLetter(args);
                break;
        }
    }

    //Возвращает коллекцию файлов указанного каталога и его подкаталогов
    private ArrayList<File> getFileCollectionFromDir(File dir, ArrayList<File> fileNamesInStrings) {

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        //рекурсивный вызов
                        getFileCollectionFromDir(file, fileNamesInStrings);
                    } else {
                        fileNamesInStrings.add(file);
                    }
                }
            }
        }
        return fileNamesInStrings;
    }

    //Возвращает коллекцию каталогов и файлов указанного каталога и его подкаталогов
    private ArrayList<File> getDirCollectionFromDir(File dir, ArrayList<File> dirNamesInStrings) {

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        //рекурсивный вызов
                        dirNamesInStrings.add(file);
                        getDirCollectionFromDir(file, dirNamesInStrings);
                    }
                }
            }
        }
        return dirNamesInStrings;
    }

    //Поиск имени файла с максимальным количеством букв ‘s’ в имени, вывод пути к нему
    private void findMaxNumberOfLettersInFileName(String[] args, String letterToFind) throws IOException {

        //получаем коллекцию файлов
        ArrayList<File> files = new ArrayList<>();
        ArrayList<File> listOfFiles = getFileCollectionFromDir(new File(args[0]), files);
        //проверям что кол-во файлов совпадает с данными Windows
        //System.out.println(listOfFiles.size());
        int countLetter = 0;
        String fileWithMaxNumberOfLetters = null;
        //для каждого имени файла находим кол-во вхождений предложенной буквы и запоминаем имя файла с наибольшим числом
        for (File filename : listOfFiles
        ) {
            if (StringUtils.countMatches(filename.getName(), letterToFind) > countLetter) {
                countLetter = StringUtils.countMatches(filename.getName(), letterToFind);
                fileWithMaxNumberOfLetters = filename.getName();
            }
        }
        String result = "The file with maximum '" + letterToFind + "' letter occurrence in " + args[0] + " is: "
                + fileWithMaxNumberOfLetters + "\n";
        System.out.print(result);
        Files.writeString(fileToWrite, result);
    }

    // Top-5 файлов с самым большим размером
    private void findTopFiveSizeFilesInDirectory(String[] args) throws IOException {

        //получаем коллекцию файлов
        ArrayList<File> files = new ArrayList<>();
        ArrayList<File> listOfFiles = getFileCollectionFromDir(new File(args[0]), files);

        //сортируем коллекцию по убыванию размера файлов - это не быстрый процесс!
        listOfFiles.sort((file1, file2) -> Long.compare(file2.length(), file1.length()));

        //выводим 5 первых результатов
        System.out.println("Top 5 file by size in " + args[0] + " :");
        Files.writeString(fileToWrite,"Top 5 file by size in " + args[0] + " :\n", StandardOpenOption.APPEND);
        listOfFiles.stream().limit(5).forEach(file -> {
            try {
                Files.writeString(fileToWrite, file.length() + " " + file.getName() + "\n", StandardOpenOption.APPEND);
                System.out.print(file.length() + " " + file.getName() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // Средний размер файла в указанной директории или любой ее поддиректории
    private void countAverageFileSizeInDirectory(String[] args) throws IOException {

        long sumSizeOfFiles = 0;

        //получаем коллекцию файлов
        ArrayList<File> files = new ArrayList<>();
        ArrayList<File> listOfFiles = getFileCollectionFromDir(new File(args[0]), files);

        //получаем общий размер файлов в каталоге
        for (File file : listOfFiles
        ) {
            sumSizeOfFiles += file.length();
        }
        //проверям что размер папки совпадает с данными Windows
        //System.out.println(sumSizeOfFiles);
        //проверям что кол-во файлов совпадает с данными Windows
        //System.out.println(listOfFiles.size());
        //выводим средний размер файла
        String result = "Average file size in " + args[0] + " is: " +
                sumSizeOfFiles / listOfFiles.size() + "\n";
        System.out.print(result);
        Files.writeString(fileToWrite,result, StandardOpenOption.APPEND);
    }

    //Количество файлов и папок разбитое по первым буквам алфавита (например на букву A – начинается 100 000 файлов и 200 папок)
    private void countFilesAndDirsByFirstLetter(String[] args) throws IOException {

        //получаем коллекцию файлов и каталогов
        ArrayList<File> files = new ArrayList<>();
        ArrayList<File> dirs = new ArrayList<>();
        ArrayList<File> listOfFiles = getFileCollectionFromDir(new File(args[0]), files);
        ArrayList<File> listOfDirs = getDirCollectionFromDir(new File(args[0]), dirs);

        // создаем карту для хранения данных - символ (key) и 2 целых числа соответствующих ему
        Map<Character, ArrayList<Integer>> filesAndDirsByFirstLetter = new HashMap<>();

        // обходим алфавит
        for (char key = 'A'; key <= 'Z'; key++) {

            // во время обхода каждый раз создаем новый ArrayList чтобы записать 2 связных поля для буквы  
            ArrayList<Integer> tempCountOfFilesAndDirs = new ArrayList<>();
            tempCountOfFilesAndDirs.add(0);
            tempCountOfFilesAndDirs.add(0);

            //цикл обходит коллекцию полученных файлов и увеличивает счетчик если первая буква равна key
            int countFilesWithFirstLetter = 0;
            for (File file : listOfFiles
            ) {
                if (file.getName().toUpperCase().charAt(0) == key) {
                    countFilesWithFirstLetter++;
                }
            }

            //цикл обходит коллекцию полученных каталогов и увеличивает счетчик если первая буква равна key
            int countDirsWithFirstLetter = 0;
            for (File file : listOfDirs
            ) {
                if (file.getName().toUpperCase().charAt(0) == key) {
                    countDirsWithFirstLetter++;
                }
            }

            // На первую позицию ArrayList записываем счетчик буквы в файлах, на вторую - в каталогах 
            tempCountOfFilesAndDirs.set(0, countFilesWithFirstLetter);
            tempCountOfFilesAndDirs.set(1, countDirsWithFirstLetter);

            // кладем ArrayList под соответствующим key
            filesAndDirsByFirstLetter.put(key, tempCountOfFilesAndDirs);
        }

        //выводим коллекцию
        String result = "Number of Files and Dirs by the first letter:\n" + filesAndDirsByFirstLetter + "\n";
        System.out.print(result);
        Files.writeString(fileToWrite, result, StandardOpenOption.APPEND);
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
