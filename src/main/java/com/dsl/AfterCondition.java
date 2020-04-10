package com.dsl;

import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.path.PathExpression;

public interface AfterCondition extends AsString {
    AfterWith with(FinalExpression... e);
    AfterMatch match(PathExpression... e);
    AfterMatch optMatch(PathExpression... e);
    AsString returns(SelectorExpression... e);
    AsString returns(Object... e);
    AsString returns(String... e);
}
