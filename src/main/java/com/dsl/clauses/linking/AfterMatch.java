package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;

public interface AfterMatch extends AsString, With, Match, Where {
    AfterMatch path(PathExpression pathExpression);
    AfterMatch path(String pathExpression);
}
