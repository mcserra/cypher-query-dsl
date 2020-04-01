package com.dsl.expressions.param;

/**
 * An expression that returns a concise object. It means that there is something selected.
 */
public interface SelectorExpression extends FinalExpression {
    FinalExpression as(String selector);
}
