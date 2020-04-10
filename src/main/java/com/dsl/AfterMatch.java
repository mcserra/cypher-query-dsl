package com.dsl;

import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;

public interface AfterMatch extends AsString {
    AfterWith with(FinalExpression... e);
    AfterCondition where(LogicalExpression... e);
    AsString returns(SelectorExpression... e);
    AsString returns(String... e);
    AsString returns(Object... e);
}
