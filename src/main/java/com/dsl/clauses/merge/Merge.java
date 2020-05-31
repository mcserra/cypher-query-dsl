package com.dsl.clauses.merge;

import com.dsl.expressions.path.PathExpression;

public interface Merge {
    AfterMerge merge(PathExpression... e);

    MergePath merge();
}
