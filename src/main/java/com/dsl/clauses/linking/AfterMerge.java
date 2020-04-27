package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;

public interface AfterMerge extends AsString, With, Match, Returns, Create, Merge {
    AfterMerge mergePath(PathExpression pathExpression);
    AfterMerge mergePath(String pathExpression);
}
