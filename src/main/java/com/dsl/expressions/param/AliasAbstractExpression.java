package com.dsl.expressions.param;

/**
 * Represents a class than can be aliased.
 */
public abstract class AliasAbstractExpression implements SelectorExpression {
    @Override
    public FinalExpression as(String selector) {
        return new As(selector, this);
    }
}
