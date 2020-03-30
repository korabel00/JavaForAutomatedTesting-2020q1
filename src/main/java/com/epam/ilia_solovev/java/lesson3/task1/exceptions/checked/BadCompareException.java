package com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked;

final public class BadCompareException extends ApplianceException {

    public String showMessageIfBadCompare(int powerLessOrEqualThanThat) {
        return ANSI_RED + "There are no appliances with power less <= " + powerLessOrEqualThanThat + ANSI_RESET;
    }
}
