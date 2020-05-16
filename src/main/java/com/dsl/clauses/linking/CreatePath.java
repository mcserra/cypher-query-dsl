package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface CreatePath {
    AfterCreate path(PathExpression pathExpression);
    AfterCreate path(String pathExpression);
}
