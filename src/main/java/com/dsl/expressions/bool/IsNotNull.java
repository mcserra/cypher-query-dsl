package com.dsl.expressions.bool;

import com.dsl.expressions.AbstractExpression;
import com.dsl.expressions.param.SelectorExpression;

public class IsNotNull extends AbstractExpression {

    public IsNotNull(final SelectorExpression e) {
        super(e, "IS NOT NULL", null);
    }

}