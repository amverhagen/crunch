package com.amverhagen.options;

import com.amverhagen.problem.EquationType;

import java.util.ArrayList;

public class Options {
    private static boolean subtraction = true;
    private static boolean multiplication = true;
    private static boolean division = true;


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
        ArrayList<EquationType> types = new ArrayList<>();
        types.add(EquationType.ADDITION);
        if (subtraction) types.add(EquationType.SUBTRACTION);
        if (multiplication) types.add(EquationType.MULTIPLICATION);
        if (division) types.add(EquationType.DIVISION);
        return types;
    }

    public String toString() {
        return "Sub is: " + subtraction + ". Mult is: " + multiplication + ". Div is: " + division;
    }
}
