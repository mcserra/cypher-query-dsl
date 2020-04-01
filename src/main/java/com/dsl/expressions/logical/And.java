package com.dsl.expressions.logical;

import com.dsl.expressions.Expression;

/**
 * 	Logical AND compares between two Booleans as expression and returns true when both expressions are true
 */
public class And extends AbstractLogicalExpression {
    public And(Expression left, Expression right) {
        super(left, LogicalOperator.AND, right);
    }
}
