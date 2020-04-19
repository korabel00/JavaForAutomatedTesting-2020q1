package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.exceptions.checked;

import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.home_appliances.HomeAppliances;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Color;

final public class ZeroPowerException extends Checked {

    public void showMessageIfPowerIsZero(HomeAppliances homeAppliances, int defaultPower) {
        System.out.println(Color.ANSI_RED.getCode() + "You cannot create an appliance with power consumption <= 0. The power of your " +
                homeAppliances.getClass().getSimpleName() + " will be set on the default number " + defaultPower +
                Color.ANSI_RESET.getCode());
    }
}
