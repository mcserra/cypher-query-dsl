package com.dsl.clauses.where;

import com.dsl.expressions.logical.LogicalExpression;

public interface AfterWhere<T> {
    T and(String expression);

    T and(LogicalExpression logicalExpression);

    T or(String expression);

    T or(LogicalExpression logicalExpression);

    T xor(String expression);

    T xor(LogicalExpression logicalExpression);
}
