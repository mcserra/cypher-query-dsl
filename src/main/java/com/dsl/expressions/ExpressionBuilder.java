package com.dsl.expressions;

import com.dsl.expressions.param.Json;
import com.dsl.expressions.param.Literal;
import com.dsl.expressions.param.ParamExpression;
import com.dsl.expressions.param.SelectorExpression;

public class ExpressionBuilder extends AbstractBooleanExpression {

    private Expression e;

    public static SelectorExpression json(Object... o) {
        return new Json(o);
    }

    public static ParamExpression literal(Object o) {
        return new Literal(o);
    }

    @Override
    public String asString() {
        return null;
    }
}
