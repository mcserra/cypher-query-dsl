package com.dsl.expressions.string;

import com.dsl.AsString;

public class ToString extends StringFunction {
    private static final String TO_STRING = "toString";

    public ToString(Object parameter) {
        super(TO_STRING, getString(parameter));
    }

    private static String getString(Object parameter) {
        if (parameter instanceof AsString) {
            return ((AsString) parameter).asString();
        }
        return parameter.toString();
    }
}
