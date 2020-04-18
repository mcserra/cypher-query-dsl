package com.dsl.clauses.links;

import com.dsl.clauses.links.AfterWhere;
import com.dsl.expressions.logical.LogicalExpression;

public interface Where {
    AfterWhere where(LogicalExpression... e);
}
