package com.amverhagen.crunch;

import com.amverhagen.problem.Equation;

import java.util.Random;


public class TestEquation {


    public static void main(String args[]) {
        Random rand;
        Equation[] equations = new Equation[100];
        for (int i = 0; i < equations.length; i++) {
            equations[i] = new Equation();
            System.out.println(equations[i].toString());
        }

        rand = new Random();
        for (int i = 0; i < 100; i++) {
            double divDiff = (((rand.nextInt(500) + 1) / 100d));
            System.out.println(divDiff);
        }
    }
}
