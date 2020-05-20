package com.dsl.expressions.string;

public class ToUpper extends StringFunction {
    private static final String TO_STRING = "toUpper";

    public ToUpper(Object parameter) {
        super(TO_STRING, getString(parameter));
    }
}
