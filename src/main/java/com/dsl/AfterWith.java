package com.dsl;

import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.path.PathExpression;

public interface AfterWith extends AsString {
    AfterMatch match(PathExpression... e);
    AsString returns(SelectorExpression... e);
    AsString returns(String... e);
    AsString returns(Object... e);
}
