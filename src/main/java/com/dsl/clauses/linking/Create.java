package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface Create {
    AfterCreate create(PathExpression... e);
}
