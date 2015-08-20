package com.amverhagen.crunch;

import com.amverhagen.options.Options;
import com.amverhagen.problem.Equation;

import java.util.ArrayList;

/**
 * Created by Andrew on 8/16/2015.
 */
public class TestEquation {

    public static void main(String args[]) {
        Options options = new Options();
        options.toggleSub();
        options.toggleMult();
        options.toggleDiv();
        Equation[] equations = new Equation[10];
        for (int i = 0; i < equations.length; i++) {
            equations[i] = new Equation();
            System.out.println(equations[i].toString());
        }
    }

}
