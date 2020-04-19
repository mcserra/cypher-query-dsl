package com.dsl.expressions.param;

/**
 * Alias of a selector. The expression is final.
 */
public class As implements FinalExpression {
    private final SelectorExpression expression;
    private final String selector;

    public As(String selector, SelectorExpression expression) {
        this.selector = selector;
        this.expression = expression;
    }

    @Override
    public String asString() {
        return String.format("%s AS %s", expression.asString(), selector);
    }
}
