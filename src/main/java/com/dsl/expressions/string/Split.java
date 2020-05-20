package com.dsl.expressions.string;

public class Split extends StringFunction {
    private static final String TO_STRING = "split";

    public Split(Object original, Object splitDelimiter) {
        super(TO_STRING, getString(original), getString(splitDelimiter));
    }
}
