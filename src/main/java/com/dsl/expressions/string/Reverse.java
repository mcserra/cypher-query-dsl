package com.dsl.expressions.string;

public class Reverse extends StringFunction {
    private static final String TO_STRING = "reverse";

    public Reverse(Object original) {
        super(TO_STRING, getString(original));
    }
}
