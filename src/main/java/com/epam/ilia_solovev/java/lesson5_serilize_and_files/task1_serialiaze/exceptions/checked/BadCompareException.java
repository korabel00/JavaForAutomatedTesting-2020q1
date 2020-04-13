package com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.exceptions.checked;

import com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.utils.Color;

final public class BadCompareException extends Checked {

    public void showMessageIfBadCompare(int powerLessOrEqualThanThat) {
        System.out.println(Color.ANSI_RED.getCode() + "There are no appliances with power less <= "
                + powerLessOrEqualThanThat + Color.ANSI_RESET.getCode());
    }
}
