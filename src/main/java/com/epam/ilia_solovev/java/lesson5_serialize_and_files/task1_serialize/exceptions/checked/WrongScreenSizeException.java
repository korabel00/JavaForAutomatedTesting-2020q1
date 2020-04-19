package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.exceptions.checked;

import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Color;

final public class WrongScreenSizeException extends Checked {

    public void showWrongScreenSizeMessage() {
        System.out.println(Color.ANSI_RED.getCode() + "Wrong screen size of a TV" + Color.ANSI_RESET.getCode());
    }
}
