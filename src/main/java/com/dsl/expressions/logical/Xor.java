package com.dsl.expressions.logical;

import com.dsl.expressions.Expression;

public class Xor extends AbstractLogicalExpression {
    public Xor(Expression left, Expression right) {
        super(left, LogicalOperator.XOR, right);
    }
}
