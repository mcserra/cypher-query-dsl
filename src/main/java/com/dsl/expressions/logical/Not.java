package com.dsl.expressions.logical;

/**
 * Not takes a single Boolean as an argument and changes its value from false to true or from true to false.
 */
public class Not extends AbstractLogicalExpression {
    public Not(com.dsl.expressions.logical.LogicalExpression expression) {
        super(null, LogicalOperator.NOT, expression);
    }
}
