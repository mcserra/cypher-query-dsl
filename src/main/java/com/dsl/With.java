package com.dsl;

import com.dsl.expressions.param.FinalExpression;

public interface With {
    AfterWith with(FinalExpression... e);
}
