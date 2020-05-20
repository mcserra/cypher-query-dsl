package com.dsl.expressions.string;

public class Substring extends StringFunction {
    private static final String TO_STRING = "substring";

    public Substring(Object original, Object start, Object length) {
        super(TO_STRING, getString(original), getString(start), getString(length));
    }

    public Substring(Object original, Object start) {
        super(TO_STRING, getString(original), getString(start));
    }
}
