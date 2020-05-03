package com.dsl.expressions.bool;

import com.dsl.expressions.param.FinalExpression;

public interface EqualityExpression extends BooleanExpression{
    EqualityExpression set(FinalExpression expression);
}
