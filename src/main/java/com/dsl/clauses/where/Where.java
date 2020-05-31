package com.dsl.clauses.where;

import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.path.PathExpression;

public interface Where<T> {
    T where(LogicalExpression logicalExpression);
    T where(String expression);
    T where(PathExpression pathExpression);
}
