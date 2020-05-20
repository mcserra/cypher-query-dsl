package com.dsl.expressions.string;

public class Replace extends StringFunction {
    private static final String TO_STRING = "replace";

    public Replace(Object original, Object search, Object replace) {
        super(TO_STRING, getString(original), getString(search), getString(replace));
    }
}
