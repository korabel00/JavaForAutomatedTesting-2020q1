package com.epam.ilia_solovev.java.lesson5.task1.utils;

import com.epam.ilia_solovev.java.lesson5.task1.home_appliances.HomeAppliances;

import java.io.*;

public class Serialize {

    public void writeObjects(HomeAppliances[] homeAppliances, String fileForKeepingObjectStates) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileForKeepingObjectStates);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(homeAppliances);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void readObjects(String fileForKeepingObjectStates) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileForKeepingObjectStates);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        System.out.println(objectInputStream.read());
        objectInputStream.close();
    }
}
