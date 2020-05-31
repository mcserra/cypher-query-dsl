package com.dsl.clauses.with;

import com.dsl.expressions.param.FinalExpression;

public interface WithSelect {
    WithAlias select(FinalExpression expression);
    WithAlias select(String expression);
}
