package com.dsl.clauses.linking;

import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.path.PathExpression;

public interface Where {
    AfterWhere where(LogicalExpression logicalExpression);
    AfterWhere where(String expression);
    AfterWhere where(PathExpression pathExpression);
}
