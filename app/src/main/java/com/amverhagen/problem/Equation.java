package com.amverhagen.problem;

import com.amverhagen.options.Options;

import java.util.ArrayList;
import java.util.Random;

public class Equation {
    private EquationType type;
    private int argOne;
    private int argTwo;
    private double correctAnswer;
    private double[] incorrectAnswers;
    private int currentIncorrectIndex;
    private Random rand;

    public Equation() {
        this.rand = new Random();
        this.type = setType();
        this.setArgs(this.type);
        this.correctAnswer = this.generateCorrectAnswer(this.type, this.argOne, this.argTwo);
        this.incorrectAnswers = this.generateIncorrectAnswers(this.type, this.correctAnswer);
        this.currentIncorrectIndex = 0;
    }

    private EquationType setType() {
        ArrayList<EquationType> types = Options.getEquationTypeList();
        int selection = (int) (Math.random() * types.size());
        return types.remove(selection);
    }

    private void setArgs(EquationType eType) {
        rand = new Random();
        if (eType == EquationType.ADDITION || eType == EquationType.SUBTRACTION) {
            this.argOne = rand.nextInt(101);
            this.argTwo = rand.nextInt(101);
        } else if (eType == EquationType.MULTIPLICATION) {
            this.argOne = rand.nextInt(16);
            this.argTwo = rand.nextInt(16);
            if (rand.nextBoolean()) this.argTwo *= -1;
        } else if (eType == EquationType.DIVISION) {
            this.argOne = rand.nextInt(101);
            this.argTwo = rand.nextInt(19) + 1;
            if (rand.nextBoolean()) this.argTwo *= -1;
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
            wrongAnswers[i] = Math.round(wrongAnswers[i] * 100) / 100d;
        }
        return wrongAnswers;
    }

    public String getEquationSyntax() {
        return argOne + " " + type.getOperation() + " " + argTwo;
    }

    public String getCorrectAnswer() {
        return Double.toString(correctAnswer);
    }

    public String getIncorrectAnswer() {
        String value;
        if (currentIncorrectIndex >= incorrectAnswers.length) currentIncorrectIndex = 0;
        value = Double.toString(incorrectAnswers[currentIncorrectIndex]);
        currentIncorrectIndex++;
        return value;
    }

    public String toString() {
        return "Equation: " + this.getEquationSyntax() + "\n" +
                "Correct Answer: " + this.getCorrectAnswer() + "\n" +
                "Incorrect Answer 1: " + this.getIncorrectAnswer() + "\n" +
                "Incorrect Answer 2: " + this.getIncorrectAnswer() + "\n" +
                "Incorrect Answer 3: " + this.getIncorrectAnswer() + "\n";
    }
}
