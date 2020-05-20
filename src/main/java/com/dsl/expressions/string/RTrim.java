package com.dsl.expressions.string;

public class RTrim extends StringFunction {
    private static final String TO_STRING = "rTrim";

    public RTrim(Object original) {
        super(TO_STRING, getString(original));
    }
}
