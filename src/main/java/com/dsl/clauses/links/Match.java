package com.dsl.clauses.links;

import com.dsl.expressions.path.PathExpression;

public interface Match {
    AfterMatch match(PathExpression... e);

    AfterMatch optMatch(PathExpression... e);
}
