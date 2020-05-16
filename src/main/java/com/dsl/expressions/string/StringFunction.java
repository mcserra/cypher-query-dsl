package com.dsl.expressions.string;


import com.dsl.AsString;
import com.dsl.StringUtils;

public abstract class StringFunction implements AsString {
    private final String expression;
    private final Object[] parameters;

    public StringFunction(String expression, Object... parameters) {
        this.expression = expression;
        this.parameters = parameters;
    }

    @Override
    public String asString() {
        return String.format("%s(%s)", expression, StringUtils.join(parameters));
    }
}
