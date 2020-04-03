package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

/**
 * Selector expression that collects expressions.
 */
public class Sum extends AggregatingExpression {
    public Sum(SelectorExpression o) {
        super("sum", o);
    }
}
