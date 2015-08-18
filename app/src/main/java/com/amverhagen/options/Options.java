package com.amverhagen.options;

import com.amverhagen.problem.EquationType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrew on 8/15/2015.
 */
public class Options implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean addition;
    private boolean subtraction;
    private boolean multiplication;
    private boolean division;

    public Options() {
        addition = true;
        subtraction = true;
        multiplication = true;
        division = true;
    }

    public void toggleAdd() {
        addition = !addition;
    }

    public void toggleSub() {
        subtraction = !subtraction;
    }

    public void toggleMult() {
        multiplication = !multiplication;
    }

    public void toggleDiv() {
        division = !division;
    }

    public ArrayList<EquationType> getEquationTypeList() {
        ArrayList<EquationType> types = new ArrayList<EquationType>();
        if (addition) types.add(EquationType.ADDITION);
        if (subtraction) types.add(EquationType.SUBTRACTION);
        if (multiplication) types.add(EquationType.MULTIPLICATION);
        if (division) types.add(EquationType.DIVISION);
        if (types.size() == 0) types.add(EquationType.ADDITION);
        return types;
    }

    public String toString() {
        return new String("Addition is " + addition + ". Sub is: " + subtraction + ". Mult is: " + multiplication + ". Div is: " + division);
    }
}
