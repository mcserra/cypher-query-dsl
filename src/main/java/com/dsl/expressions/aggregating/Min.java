package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

/**
 * Selector expression that collects expressions.
 */
public class Min extends AggregatingExpression {
    public Min(SelectorExpression o) {
        super("min", o);
    }
}
