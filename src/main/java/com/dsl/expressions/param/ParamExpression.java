package com.dsl.expressions.param;

import com.dsl.expressions.bool.AbstractBooleanExpression;

/**
 * Represents an expression that can be used in combination with another expression, representing a Boolean or Logical
 * expression.
 */
public class ParamExpression extends AbstractBooleanExpression {
    protected SelectorExpression left;
    protected String operator;
    protected SelectorExpression right;

    public ParamExpression(SelectorExpression value) {
        this.left = value;
        this.operator = null;
        this.right = null;
    }

    public ParamExpression(SelectorExpression left, String operator, SelectorExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String asString() {
        return String.format(
            "%s%s%s",
            left.asString(),
            operator == null ? "" : String.format(" %s ", operator),
            right == null ? "" : right.asString()
        );
    }
}
