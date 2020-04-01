package com.dsl.expressions.logical;

import com.dsl.expressions.Expression;

/**
 * Not takes a single Boolean as an argument and changes its value from false to true or from true to false.
 */
public class Not extends AbstractLogicalExpression {
    public Not(Expression expression) {
        super(null, LogicalOperator.NOT, expression);
    }
}
