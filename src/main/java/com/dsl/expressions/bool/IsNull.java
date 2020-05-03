package com.dsl.expressions.bool;

import com.dsl.expressions.AbstractExpression;
import com.dsl.expressions.param.SelectorExpression;

public class IsNull extends AbstractExpression {

    public IsNull(final SelectorExpression e) {
        super(e, "IS NULL", null);
    }

}
