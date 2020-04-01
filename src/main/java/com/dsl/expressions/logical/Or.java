package com.dsl.expressions.logical;

import com.dsl.expressions.Expression;

/**
 * Logical OR compares between two Booleans as expression and returns true when one of the expression is true.
 */
public class Or extends AbstractLogicalExpression {
    public Or(Expression left, Expression right) {
        super(left, LogicalOperator.OR, right);
    }
}
