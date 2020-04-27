package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface AfterCreate extends With, Match, Returns, Create, Merge {
    AfterCreate createPath(PathExpression pathExpression);
    AfterCreate createPath(String pathExpression);
}
