package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface MatchPath {
    AfterMatch path(PathExpression pathExpression);
    AfterMatch path(String pathExpression);
}
