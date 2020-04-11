package com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.exceptions.checked;

import com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.home_appliances.HomeAppliances;
import com.epam.ilia_solovev.java.lesson5_serilize_and_files.task1_serialiaze.utils.Color;


final public class ApplianceIsOffException extends Checked {

    public void showTurnMeOnMessage(HomeAppliances homeAppliances) {
        System.out.println(Color.ANSI_RED.getCode() + "Before doing work " + homeAppliances.getClass().getSimpleName() +
                " needs to be turned on. But ok, I will do it for you." + Color.ANSI_RESET.getCode());
    }
}
