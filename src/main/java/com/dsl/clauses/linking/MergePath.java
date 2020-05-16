package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface MergePath {
    AfterMerge path(PathExpression pathExpression);

    AfterMerge path(String pathExpression);
}
