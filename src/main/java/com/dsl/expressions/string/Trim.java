package com.dsl.expressions.string;

public class Trim extends StringFunction {
    private static final String TO_STRING = "trim";

    public Trim(Object parameter) {
        super(TO_STRING, getString(parameter));
    }
}
