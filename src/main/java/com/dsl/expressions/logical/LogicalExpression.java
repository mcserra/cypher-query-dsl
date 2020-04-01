package com.dsl.expressions.logical;

import com.dsl.expressions.Expression;

/**
 * Expressions with logical operators.
 */
public interface LogicalExpression extends Expression {
    LogicalExpression and(LogicalExpression expression);

    LogicalExpression or(LogicalExpression expression);

    LogicalExpression not(LogicalExpression expression);
}
