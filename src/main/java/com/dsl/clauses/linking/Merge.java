package com.dsl.clauses.linking;

import com.dsl.expressions.path.PathExpression;

public interface Merge {
    AfterMerge merge(PathExpression... e);
}
