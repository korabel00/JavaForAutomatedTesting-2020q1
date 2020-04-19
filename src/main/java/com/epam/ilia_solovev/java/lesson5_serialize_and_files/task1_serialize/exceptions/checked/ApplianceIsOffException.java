package com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.exceptions.checked;

import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.home_appliances.HomeAppliances;
import com.epam.ilia_solovev.java.lesson5_serialize_and_files.task1_serialize.utils.Color;


final public class ApplianceIsOffException extends Checked {

    public void showTurnMeOnMessage(HomeAppliances homeAppliances) {
        System.out.println(Color.ANSI_RED.getCode() + "Before doing work " + homeAppliances.getClass().getSimpleName() +
                " needs to be turned on. But ok, I will do it for you." + Color.ANSI_RESET.getCode());
    }
}
