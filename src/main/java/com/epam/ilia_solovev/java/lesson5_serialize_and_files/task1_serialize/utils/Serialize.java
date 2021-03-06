package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils;

import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.home_appliances.HomeAppliances;

import java.io.*;

public class Serialize {

    public void writeObjects(HomeAppliances[] homeAppliances, String fileForKeepingObjectStates) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(fileForKeepingObjectStates);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(homeAppliances);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void readObjects(String fileForKeepingObjectStates) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(fileForKeepingObjectStates);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        HomeAppliances[] homeAppliances = (HomeAppliances[]) objectInputStream.readObject();

        for (HomeAppliances thing : homeAppliances
             ) {
            System.out.println(thing.getClass().getSimpleName() + " " + thing.getBrand() + " " + thing.getModel() +
                    " " + thing.getPowerConsumption());
        }
        objectInputStream.close();
    }
}
