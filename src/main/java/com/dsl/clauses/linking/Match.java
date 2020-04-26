package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface Match {
    AfterMatch match(PathExpression... pathExpressions);

    AfterMatch optMatch(PathExpression... pathExpressions);
}
