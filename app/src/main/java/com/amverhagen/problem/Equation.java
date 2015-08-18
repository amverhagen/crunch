package com.amverhagen.problem;

import com.amverhagen.options.Options;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrew on 8/15/2015.
 */
public class Equation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Options options;
    private EquationType type;
    private int argOne;
    private int argTwo;
    private double correctAnswer;
    private double[] incorrectAnswers;

    public Equation(Options ops) {
        if (ops == null) this.options = new Options();
        else this.options = ops;
        this.type = setType(this.options);
        this.setArgs(this.type);
        this.correctAnswer = this.generateCorrectAnswer(this.type, this.argOne, this.argTwo);
        this.incorrectAnswers = this.generateIncorrectAnswers(this.type, this.correctAnswer);
    }

    private EquationType setType(Options ops) {
        ArrayList<EquationType> types = ops.getEquationTypeList();
        int selection = (int) (Math.random() * types.size());
        return types.remove(selection);
    }

    private void setArgs(EquationType eType) {
        if (eType == EquationType.ADDITION || eType == EquationType.SUBTRACTION) {
            this.argOne = (int) Math.ceil(Math.random() * 99) + 1;
            this.argTwo = (int) Math.ceil(Math.random() * 99) + 1;
        } else if (eType == EquationType.MULTIPLICATION || eType == EquationType.DIVISION) {
            this.argOne = (int) Math.ceil(Math.random() * 14) + 1;
            this.argTwo = (int) Math.ceil(Math.random() * 14) + 1;
        }
    }

    private double generateCorrectAnswer(EquationType eType, double a1, double a2) {
        double answer = 0;
        if (eType == EquationType.ADDITION) {
            answer = a1 + a2;
        } else if (eType == EquationType.SUBTRACTION) {
            answer = a1 - a2;
        } else if (eType == EquationType.MULTIPLICATION) {
            answer = a1 * a2;
        } else if (eType == EquationType.DIVISION) {
            if (a2 == 0) return 0;
            answer = a1 / a2;
        }
        return Math.round(answer * 100) / 100d;
    }

    private double[] generateIncorrectAnswers(EquationType eType, double correct) {
        double[] wrongAnswers = new double[3];
        for (int i = 0; i < wrongAnswers.length; i++) {
            int sign = (Math.random() < 0.5) ? -1 : 1;
            int diff = (int) (Math.ceil(Math.random() * 9 + 1) * sign);
            wrongAnswers[i] = correct + diff;
        }
        return wrongAnswers;
    }

    public String getEquationSyntax() {
        String syntax = argOne + " " + type.getOperation() + " " + argTwo;
        return syntax;
    }

    public String getCorrectAnswer() {
        return Double.toString(correctAnswer);
    }

    public String getIncorrectAnswer(int index) {
        return Double.toString(incorrectAnswers[index]);
    }

    public String toString() {
        return new String("Arg1 is: " + argOne + ". Arg2 is: " + argTwo + ". Type is: " + type + " Answer is: " + correctAnswer
                + ". Incorrects are: " + incorrectAnswers[0] + " " + incorrectAnswers[1] + " " + incorrectAnswers[2]);
    }
}
