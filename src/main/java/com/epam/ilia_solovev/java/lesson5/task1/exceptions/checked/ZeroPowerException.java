package com.epam.ilia_solovev.java.lesson5.task1.exceptions.checked;

import com.epam.ilia_solovev.java.lesson5.task1.home_appliances.HomeAppliances;
import com.epam.ilia_solovev.java.lesson5.task1.utils.Color;

final public class ZeroPowerException extends Checked {

    public void showMessageIfPowerIsZero(HomeAppliances homeAppliances, int defaultPower) {
        System.out.println(Color.ANSI_RED.getCode() + "You cannot create an appliance with power consumption <= 0. The power of your " +
                homeAppliances.getClass().getSimpleName() + " will be set on the default number " + defaultPower +
                Color.ANSI_RESET.getCode());
    }
}
