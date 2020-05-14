package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface AfterCreate extends With, Match, Returns, Create, Merge, Unwind {
    AfterCreate path(PathExpression pathExpression);
    AfterCreate path(String pathExpression);
}
