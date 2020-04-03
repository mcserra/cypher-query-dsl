package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

/**
 * Selector expression that collects expressions.
 */
public class Collect extends AggregatingExpression {
    public Collect(SelectorExpression o) {
        super("collect", o);
    }
}
