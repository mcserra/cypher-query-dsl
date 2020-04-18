package com.dsl.clauses.links;

import com.dsl.expressions.param.FinalExpression;

public interface With {
    AfterWith with(FinalExpression... e);
}
