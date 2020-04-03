package com.dsl.expressions;

import com.dsl.expressions.param.As;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;

/**
 * Represents a class than can be aliased.
 */
public abstract class AliasAbstractExpression implements SelectorExpression {
    @Override
    public FinalExpression as(String selector) {
        return new As(selector, this);
    }
}
