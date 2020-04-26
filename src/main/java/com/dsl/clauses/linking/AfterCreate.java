package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface AfterCreate extends With, Match, Returns {
    AfterCreate exp(PathExpression pathExpression);
    AfterCreate exp(String pathExpression);
}
