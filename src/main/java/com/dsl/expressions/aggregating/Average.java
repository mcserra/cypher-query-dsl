package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

public class Average extends AggregatingExpression {
    public Average(SelectorExpression o) {
        super("avg", o);
    }
}
