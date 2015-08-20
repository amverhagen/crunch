package com.amverhagen.options;

import com.amverhagen.problem.EquationType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrew on 8/15/2015.
 */
public class Options {
    private static boolean subtraction = false;
    private static boolean multiplication = false;
    private static boolean division = false;


    public static void toggleSub() {
        subtraction = !subtraction;
    }

    public static void toggleMult() {
        multiplication = !multiplication;
    }

    public static void toggleDiv() {
        division = !division;
    }

    public static boolean getSubSetting() {
        return subtraction;
    }

    public static boolean getMultSetting() {
        return multiplication;
    }

    public static boolean getDivSetting() {
        return division;
    }

    public static ArrayList<EquationType> getEquationTypeList() {
        ArrayList<EquationType> types = new ArrayList<EquationType>();
        types.add(EquationType.ADDITION);
        if (subtraction) types.add(EquationType.SUBTRACTION);
        if (multiplication) types.add(EquationType.MULTIPLICATION);
        if (division) types.add(EquationType.DIVISION);
        return types;
    }

    public String toString() {
        return new String("Sub is: " + subtraction + ". Mult is: " + multiplication + ". Div is: " + division);
    }
}
