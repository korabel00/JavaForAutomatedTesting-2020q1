package com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked;

final public class WrongScreenSizeException extends ApplianceException {

    public String showWrongScreenSizeMessage() {
        return ANSI_RED + "Wrong screen size of a TV" + ANSI_RESET;
    }
}
