package com.dsl.expressions;

import com.dsl.expressions.bool.*;
import com.dsl.expressions.logical.ConditionalAbstractExpression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.As;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Literal;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.param.Variable;

/**
 * Common methods for all expressions
 */
public abstract class AbstractBooleanExpression extends ConditionalAbstractExpression implements BooleanExpression,
    SelectorExpression, LogicalExpression {

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

    @Override
    public LogicalExpression in(Variable o) {
        return new In(this, o);
    }

    @Override
    public LogicalExpression contains(String s){
        return new Contains(this, new Literal(s));
    }

    @Override
    public LogicalExpression matchRegex(String s){
        return new AbstractExpression(this, "=~", new Literal(s));
    }

    @Override
    public LogicalExpression isNull(){return new IsNull(this);}

    @Override
    public LogicalExpression isNotNull(){return new IsNotNull(this);}


}
