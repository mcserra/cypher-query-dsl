package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.expressions.bool.EqualityExpression;
import com.dsl.expressions.path.PathExpression;

public interface AfterMerge extends AsString, With, Match, Returns, Create, Merge, Unwind {
    AfterMerge path(PathExpression pathExpression);

    AfterMerge path(String pathExpression);

    AfterMerge onMatch(EqualityExpression... equalityExpressions);

    AfterMerge onCreate(EqualityExpression... equalityExpressions);

    AfterMerge onMatch(String equalityExpressions);

    AfterMerge onCreate(String equalityExpressions);
}
