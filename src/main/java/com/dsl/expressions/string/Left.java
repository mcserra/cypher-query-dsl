package com.dsl.expressions.string;

public class Left extends StringFunction {
    private static final String TO_STRING = "left";

    public Left(Object original, Object length) {
        super(TO_STRING, getString(original), getString(length));
    }
}
