package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

/**
 * Selector expression that collects expressions.
 */
public class Max extends AggregatingExpression {
    public Max(SelectorExpression o) {
        super("max", o);
    }
}
