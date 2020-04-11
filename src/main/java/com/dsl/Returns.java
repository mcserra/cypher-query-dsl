package com.dsl;

import com.dsl.expressions.param.SelectorExpression;

public interface Returns {

    AfterReturns returns(SelectorExpression... e);

    AfterReturns returns(String... e);

    AfterReturns returns(Object... e);
}
