package com.dsl.clauses.linking;

import com.dsl.expressions.param.FinalExpression;

public interface WithSelect {
    WithAlias select(FinalExpression expression);
    WithAlias select(String expression);
}
