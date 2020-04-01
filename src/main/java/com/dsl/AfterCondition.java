package com.dsl;

import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.path.PathExpression;

public interface AfterCondition extends AsString {
    AfterWith with(FinalExpression... e);
    AfterMatch matches(PathExpression... e);
    ClauseBuilder returns(Object... e);
    ClauseBuilder returns(String... e);
    ClauseBuilder returns(SelectorExpression... e);
}
