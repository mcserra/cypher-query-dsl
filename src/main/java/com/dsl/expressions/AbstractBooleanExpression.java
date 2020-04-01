package com.dsl.expressions;

import com.dsl.expressions.bool.BooleanExpression;
import com.dsl.expressions.logical.And;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.logical.Not;
import com.dsl.expressions.logical.Or;
import com.dsl.expressions.param.As;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Literal;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.special.In;

/**
 * Common methods for all expressions
 */
public abstract class AbstractBooleanExpression implements BooleanExpression, LogicalExpression,
    SelectorExpression {

    @Override
    public LogicalExpression eq(Object o) {
        return new AbstractExpression(this, "=", new Literal(o));
    }

    @Override
    public LogicalExpression eq(Expression expression) {
        return new AbstractExpression(this, "=", expression);
    }

    @Override
    public LogicalExpression ne(Object o) {
        return new AbstractExpression(this, "!=", new Literal(o));
    }

    @Override
    public LogicalExpression ne(Expression expression) {
        return new AbstractExpression(this, "!=", expression);
    }

    @Override
    public LogicalExpression lt(Object o) {
        return new AbstractExpression(this, "<", new Literal(o));
    }

    @Override
    public LogicalExpression lte(Object o) {
        return new AbstractExpression(this, "<=", new Literal(o));
    }

    @Override
    public LogicalExpression bt(Object o) {
        return new AbstractExpression(this, ">", new Literal(o));
    }

    @Override
    public LogicalExpression lt(Expression expression) {
        return new AbstractExpression(this, "<", expression);
    }

    @Override
    public LogicalExpression lte(Expression expression) {
        return new AbstractExpression(this, "<=", expression);
    }

    @Override
    public LogicalExpression bt(Expression expression) {
        return new AbstractExpression(this, ">", expression);
    }

    @Override
    public LogicalExpression bte(Expression expression) {
        return new AbstractExpression(this, ">=", expression);
    }

    @Override
    public LogicalExpression bte(Object o) {
        return new AbstractExpression(this, ">=", new Literal(o));
    }

    @Override
    public FinalExpression as(String selector) {
        return new As(selector, this);
    }

    @Override
    public LogicalExpression in(SelectorExpression... expressions) {
        return new In(this, expressions);
    }

    @Override
    public LogicalExpression in(Object... o) {
        return new In(this, o);
    }

    public LogicalExpression and(LogicalExpression expression) {
        return new And(this, expression);
    }

    public LogicalExpression or(LogicalExpression expression) {
        return new Or(this, expression);
    }

    public LogicalExpression not(LogicalExpression expression) {
        return new Not(expression);
    }
}
