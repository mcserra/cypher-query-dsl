package com.dsl.clauses.links;

import com.dsl.expressions.path.PathExpression;

public interface Create {
    AfterMatch create(PathExpression... e);
}
