package com.amverhagen.problem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrew on 8/17/2015.
 */
public class EquationWrapper implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Equation> equations;

    public EquationWrapper(ArrayList<Equation> equations) {
        this.equations = equations;
    }

    public ArrayList<Equation> getEquations() {
        return this.equations;
    }
}
