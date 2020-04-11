package com.epam.ilia_solovev.java.lesson3_exceptions.task1.exceptions.checked;

import com.epam.ilia_solovev.java.lesson3_exceptions.task1.utils.Color;

final public class WrongScreenSizeException extends Checked {

    public void showWrongScreenSizeMessage() {
        System.out.println(Color.ANSI_RED.getCode() + "Wrong screen size of a TV" + Color.ANSI_RESET.getCode());
    }
}
