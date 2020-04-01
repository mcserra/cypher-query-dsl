package com.dsl.expressions.param;

import com.dsl.StringUtils;

/**
 * Selector expression that collects expressions.
 */
public class Collect extends AliasAbstractExpression implements SelectorExpression {

    private SelectorExpression[] e;

    public Collect(SelectorExpression... o) {
        this.e = o;
    }

    @Override
    public String asString() {
        return String.format("collect(%s)", String.join(", ", StringUtils.asString(e)));
    }
}
