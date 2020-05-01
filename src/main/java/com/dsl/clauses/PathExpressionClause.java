package com.dsl.clauses;

import com.dsl.expressions.path.PathExpression;

public interface PathExpressionClause {
    void addExpression(final PathExpression e);

    void addExpression(final String e);
}
