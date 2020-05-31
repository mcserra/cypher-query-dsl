package com.dsl.clauses.match;

import com.dsl.expressions.path.PathExpression;

public interface Match {
    AfterMatchGeneral match(PathExpression... pathExpressions);

    AfterMatchGeneral optMatch(PathExpression... pathExpressions);

    MatchPath match();

    MatchPath optMatch();
}
