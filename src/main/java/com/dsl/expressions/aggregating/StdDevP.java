package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

/**
 * Selector expression that collects expressions.
 */
public class StdDevP extends AggregatingExpression {
    public StdDevP(SelectorExpression o) {
        super("stDevP", o);
    }
}
