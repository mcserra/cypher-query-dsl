package com.dsl.clauses.linking;

import com.dsl.expressions.Expression;

public interface Returns {

    AfterReturns returns(Expression... e);

    AfterReturns returns(String... e);

    AfterReturns returns(Object... e);
}
