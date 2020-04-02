package com.dsl;

import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.path.PathExpression;

public interface AfterWith {
    AfterMatch match(PathExpression... e);
    AsString returns(SelectorExpression... e);
    AsString returns(String... e);
    AsString returns(Object... e);
}
