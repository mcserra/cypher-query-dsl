package com.dsl.clauses.merge;

import com.dsl.AsString;
import com.dsl.clauses.creates.Create;
import com.dsl.clauses.match.Match;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.set.Set;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.with.With;
import com.dsl.expressions.bool.EqualityExpression;
import com.dsl.expressions.path.PathExpression;

public interface AfterMerge extends AsString, With, Match, Returns, Create, Merge, Unwind, Set {
    AfterMerge path(PathExpression pathExpression);

    AfterMerge path(String pathExpression);

    AfterMerge onMatch(EqualityExpression... equalityExpressions);

    AfterMerge onCreate(EqualityExpression... equalityExpressions);

    AfterMerge onMatch(String equalityExpressions);

    AfterMerge onCreate(String equalityExpressions);
}
