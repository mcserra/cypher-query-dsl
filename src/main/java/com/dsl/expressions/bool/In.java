package com.dsl.expressions.bool;

import com.dsl.expressions.AbstractExpression;
import com.dsl.expressions.Expression;
import com.dsl.expressions.ExpressionGalore;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.param.Variable;

public class In extends AbstractExpression {

    public In(final SelectorExpression e, final Expression... expressions) {
        super(e, " IN ", new ExpressionGalore(expressions));
    }


    public In(final SelectorExpression e, final Variable var) {
        super(e, " IN ", var);
    }

    public In(final SelectorExpression e, final Object... objects) {
        super(e, " IN ", new ExpressionGalore(objects));
    }

    @Override
    public String asString() {
        if (getRight() instanceof Variable) {
            return String.format("%s IN %s", getLeft().asString(), getRight().asString());
        }
        return String.format("%s IN [%s]", getLeft().asString(), getRight().asString());
    }
}
