package com.dsl.expressions.logical;

import com.dsl.expressions.param.Selector;

/**
 * Not takes a single Boolean as an argument and changes its value from false to true or from true to false.
 */
public class Not extends AbstractLogicalExpression {
    public Not(final com.dsl.expressions.logical.LogicalExpression expression) {
        super(null, LogicalOperator.NOT, expression);
    }

    public Not(final String expression) {
        super(null, LogicalOperator.NOT, new Selector(expression));
    }
}
