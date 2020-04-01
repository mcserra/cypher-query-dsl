package com.dsl.expressions;

import com.dsl.AsString;

/**
 * Represents an expression, with the right and left side expression and the operator.
 * If no operator or no right expression exists, than this represents an ExpressionParameter.
 */
public class AbstractExpression extends AbstractBooleanExpression implements AsString {
    protected Expression left;
    protected String operator;
    protected Expression right;

    public AbstractExpression(Expression value) {
        this.left = value;
        this.operator = null;
        this.right = null;
    }

    public AbstractExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
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
