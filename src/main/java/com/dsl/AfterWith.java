package com.dsl;

import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.path.PathExpression;

public interface AfterWith {
    AfterMatch matches(PathExpression... e);
    ClauseBuilder returns(SelectorExpression... e);
    ClauseBuilder returns(String... e);
    ClauseBuilder returns(Object... e);
}
