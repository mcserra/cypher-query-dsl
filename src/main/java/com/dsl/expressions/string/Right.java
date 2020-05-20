package com.dsl.expressions.string;

public class Right extends StringFunction {
    private static final String TO_STRING = "right";

    public Right(Object original, Object length) {
        super(TO_STRING, getString(original), getString(length));
    }
}
