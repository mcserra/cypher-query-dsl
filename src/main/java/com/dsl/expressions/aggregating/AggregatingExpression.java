package com.dsl.expressions.aggregating;

import com.dsl.expressions.AliasAbstractExpression;
import com.dsl.expressions.param.SelectorExpression;

public abstract class AggregatingExpression extends AliasAbstractExpression implements SelectorExpression {
    protected SelectorExpression e;
    protected String expression;

    public AggregatingExpression(String expression, SelectorExpression o) {
        this.e = o;
        this.expression = expression;
    }

    @Override
    public String asString() {
        return String.format("%s(%s)", expression, e.asString());
    }
}
