package com.dsl.clauses.linking;

import com.dsl.expressions.Expression;

public interface Returns {

    ReturnAlias returns(Expression... e);

    ReturnAlias returns(String... e);

    ReturnAlias returns(Object... e);
}
