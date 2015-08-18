package com.amverhagen.problem;

/**
 * Created by Andrew on 8/15/2015.
 */
public enum EquationType {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");
    private final String operation;

    EquationType(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return this.operation;
    }
}
