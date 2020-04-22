package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.expressions.param.FinalExpression;

public interface AfterWith extends Match, AsString, With, Returns {
    WithAlias select(FinalExpression expression);
    WithAlias select(String expression);
}
