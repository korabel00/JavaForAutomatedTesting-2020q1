package com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked;

import com.epam.ilia_solovev.java.lesson3.task1.home_appliances.HomeAppliances;

final public class ZeroPowerException extends ApplianceException {

    public String showMessageIfPowerIsZero(HomeAppliances homeAppliances, int defaultPower) {
        return ANSI_RED + "You cannot create an appliance with power consumption <= 0. The power of your " +
                homeAppliances.getClass().getSimpleName() + " will be set on the default number " + defaultPower +
                ANSI_RESET;
    }
}
