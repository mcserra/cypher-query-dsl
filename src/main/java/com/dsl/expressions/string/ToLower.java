package com.dsl.expressions.string;

public class ToLower extends StringFunction {
    private static final String TO_STRING = "toLower";

    public ToLower(Object parameter) {
        super(TO_STRING, getString(parameter));
    }
}
