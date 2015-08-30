package com.amverhagen.crunch;

import com.amverhagen.options.Options;
import com.amverhagen.problem.Equation;

import java.util.ArrayList;

/**
 * Created by Andrew on 8/16/2015.
 */
public class TestEquation {

    public static void main(String args[]) {
        Equation[] equations = new Equation[100];
        for (int i = 0; i < equations.length; i++) {
            equations[i] = new Equation();
            System.out.println(equations[i].toString());
        }
    }
}
