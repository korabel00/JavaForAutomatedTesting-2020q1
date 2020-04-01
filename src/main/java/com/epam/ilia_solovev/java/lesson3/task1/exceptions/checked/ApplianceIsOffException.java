package com.epam.ilia_solovev.java.lesson3.task1.exceptions.checked;

import com.epam.ilia_solovev.java.lesson3.task1.home_appliances.HomeAppliances;
import com.epam.ilia_solovev.java.lesson3.task1.utils.Color;


final public class ApplianceIsOffException extends Checked {

    public void showTurnMeOnMessage(HomeAppliances homeAppliances) {
        System.out.println(Color.ANSI_RED.getCode() + "Before doing work " + homeAppliances.getClass().getSimpleName() +
                " needs to be turned on. But ok, I will do it for you." + Color.ANSI_RESET.getCode());
    }
}
