package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

/**
 * Selector expression that collects expressions.
 */
public class StdDev extends AggregatingExpression {
    public StdDev(SelectorExpression o) {
        super("stDev", o);
    }
}
