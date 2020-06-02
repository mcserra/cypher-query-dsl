package com.dsl.clauses.with;

import com.dsl.expressions.param.FinalExpression;

/**
 * After with() which can only be proceeded by a select(..).
 */
public interface WithSelect {
    WithSelectAlias select(FinalExpression expression);
    WithSelectAlias select(String expression);
}
