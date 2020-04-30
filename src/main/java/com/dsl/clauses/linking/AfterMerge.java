package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;

public interface AfterMerge extends AsString, With, Match, Returns, Create, Merge {
    AfterMerge path(PathExpression pathExpression);
    AfterMerge path(String pathExpression);
}
