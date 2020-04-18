package com.dsl.clauses.links;

import com.dsl.expressions.logical.LogicalExpression;

public interface Where {
    AfterWhere where(LogicalExpression... e);
}
