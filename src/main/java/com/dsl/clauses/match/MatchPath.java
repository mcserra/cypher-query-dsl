package com.dsl.clauses.match;

import com.dsl.expressions.path.PathExpression;

public interface MatchPath {
    AfterMatchGeneral path(PathExpression pathExpression);
    AfterMatchGeneral path(String pathExpression);
}
