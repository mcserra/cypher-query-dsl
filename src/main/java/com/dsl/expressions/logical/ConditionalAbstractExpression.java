package com.dsl.expressions.logical;

import com.dsl.expressions.param.Selector;

/**
 * Represents a logical expression. Like And/Or/Not.
 */
public abstract class ConditionalAbstractExpression implements LogicalExpression {

    @Override
    public LogicalExpression xor(LogicalExpression expression) {
        return new Xor(this, expression);
    }

    @Override
    public LogicalExpression and(LogicalExpression expression) {
        return new And(this, expression);
    }

    @Override
    public LogicalExpression or(LogicalExpression expression) {
        return new Or(this, expression);
    }

    @Override
    public LogicalExpression and(String expression) {
        return new And(this, new Selector(expression));
    }

    @Override
    public LogicalExpression or(String expression) {
        return new Or(this, new Selector(expression));
    }

    @Override
    public LogicalExpression xor(String expression) {
        return new Xor(this, new Selector(expression));
    }
}
