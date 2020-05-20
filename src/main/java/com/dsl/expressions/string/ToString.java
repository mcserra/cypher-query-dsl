package com.dsl.expressions.string;

public class ToString extends StringFunction {
    private static final String TO_STRING = "toString";

    public ToString(Object parameter) {
        super(TO_STRING, getString(parameter));
    }
}
