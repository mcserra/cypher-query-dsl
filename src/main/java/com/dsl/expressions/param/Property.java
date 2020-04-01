package com.dsl.expressions.param;

/**
 * A property of a selected variable.
 */
public class Property extends ParamExpression implements IdentifierExpression, SelectorExpression {

    public Property(Selector value, Selector parent) {
        super(parent, ".", value);
    }

    @Override
    public String asString() {
        return String.format("%s%s%s", left.asString(), operator, right.asString());
    }
}
