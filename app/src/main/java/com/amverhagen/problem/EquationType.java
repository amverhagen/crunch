package com.amverhagen.problem;

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
