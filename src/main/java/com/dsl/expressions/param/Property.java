package com.dsl.expressions.param;

/**
 * A property of a selected variable.
 */
public class Property extends ParamExpression implements IdentifierExpression, SelectorExpression {

    public Property(final Selector value, final Selector parent) {
        super(parent, ".", value);
    }

    public Property(final String s) {
        super(new Selector(s), "", new Selector(""));
    }

    @Override
    public String asString() {
        return String.format("%s%s%s", left.asString(), operator, right.asString());
    }
}
