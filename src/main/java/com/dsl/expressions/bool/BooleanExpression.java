package com.dsl.expressions.bool;

import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.param.Variable;

/**
 * An expression that returns true or false
 */
public interface BooleanExpression extends Expression {
    LogicalExpression eq(Object o);

    LogicalExpression eq(Expression expression);

    LogicalExpression ne(Object o);

    LogicalExpression ne(Expression expression);

    LogicalExpression lt(Object o);

    LogicalExpression lt(Expression expression);

    LogicalExpression lte(Object o);

    LogicalExpression lte(Expression expression);

    LogicalExpression bt(Object o);

    LogicalExpression bt(Expression expression);

    LogicalExpression bte(Object o);

    LogicalExpression bte(Expression expression);

    LogicalExpression in(SelectorExpression... expressions);

    LogicalExpression in(Object... expressions);
    LogicalExpression in(Variable expressions);
}
