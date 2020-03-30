package com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked;

import com.epam.ilia_solovev.java.lesson3.task1.home_appliances.HomeAppliances;

final public class ApplianceIsOffException extends ApplianceException {

    public String showTurnMeOnMessage(HomeAppliances homeAppliances) {
        return ANSI_RED + "Before doing work " + homeAppliances.getClass().getSimpleName() + " needs to be turned on. " +
                "But ok, I will do it for you." + ANSI_RESET;
    }
}
