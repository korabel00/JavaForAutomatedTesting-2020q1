package com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked;

import com.epam.ilia_solovev.java.lesson3.task1.home_appliances.HomeAppliances;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Colorable;

final public class ApplianceException extends Checked implements Colorable {

    public String messageIfPowerIsZero(HomeAppliances homeAppliances, int defaultPower) {
        return ANSI_RED + "You cannot create an appliance with power consumption <= 0. The power of your " +
                homeAppliances.getClass().getSimpleName() + " will be set on the default number " + defaultPower +
                ANSI_RESET;
    }

    public String messageForBadCompare(int powerLessOrEqualThanThat) {
        return ANSI_RED + "There are no appliances with power less <= " + powerLessOrEqualThanThat + ANSI_RESET;
    }

    public String turnMeOnException(HomeAppliances homeAppliances) {
        return ANSI_RED + "Before doing work " + homeAppliances.getClass().getSimpleName() + " needs to be turned on. " +
                "But ok, I will do it for you." + ANSI_RESET;
    }

    public String wrongScreenSize() {
        return ANSI_RED + "Wrong screen size of a TV" + ANSI_RESET;
    }
}
