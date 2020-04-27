package com.dsl.expressions.bool;

import com.dsl.expressions.AbstractExpression;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Literal;
import com.dsl.expressions.param.SelectorExpression;

public class Contains extends AbstractExpression {

    public Contains(final SelectorExpression e, final Expression ex) {
        super(e, "CONTAINS", ex);
    }

}
