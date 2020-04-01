package com.dsl;

import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.FinalExpression;

public interface AfterMatch extends AsString {
    AfterWith with(FinalExpression... e);

    AfterCondition where(LogicalExpression... e);

    ClauseBuilder returns(FinalExpression... e);
    ClauseBuilder returns(String... e);
}
