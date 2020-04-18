package com.dsl.clauses.links;

import com.dsl.expressions.param.SelectorExpression;

public interface Returns {

    AfterReturns returns(SelectorExpression... e);

    AfterReturns returns(String... e);

    AfterReturns returns(Object... e);
}
