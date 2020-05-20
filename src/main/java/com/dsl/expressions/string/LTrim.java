package com.dsl.expressions.string;

public class LTrim extends StringFunction {
    private static final String TO_STRING = "lTrim";

    public LTrim(Object original) {
        super(TO_STRING, getString(original));
    }
}
