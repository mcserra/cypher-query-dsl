package com.dsl.expressions.special;

import com.dsl.expressions.AbstractExpression;
import com.dsl.expressions.Expression;
import com.dsl.expressions.ExpressionGalore;
import com.dsl.expressions.param.SelectorExpression;

public class In extends AbstractExpression {

    public In(final SelectorExpression e, final Expression... expressions) {
        super(e, " IN ", new ExpressionGalore(expressions));
    }

    public In(final SelectorExpression e, final Object... objects) {
        super(e, " IN ", new ExpressionGalore(objects));
    }

    @Override
    public String asString() {
        return String.format("%s IN [%s]", getLeft().asString(), getRight().asString());
    }
}
