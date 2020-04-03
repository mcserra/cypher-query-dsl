package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

/**
 * Selector expression that collects expressions.
 */
public class Count extends AggregatingExpression {
    public Count(SelectorExpression o) {
        super("count", o);
    }
}
