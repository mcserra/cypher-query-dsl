package com.dsl.expressions;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.expressions.param.Literal;

/**
 * Just multiple expressions.
 */
public class ExpressionGalore extends AbstractBooleanExpression implements AsString {
    public final Expression[] expressions;

    public ExpressionGalore(Expression[] expressions) {
        this.expressions = expressions;
    }

    public ExpressionGalore(Object[] objects) {
        expressions = new Expression[objects.length];
        fillWithLiterals(objects);
    }

    private void fillWithLiterals(Object[] objects) {
        for (int i = 0; i < objects.length; i++) {
            this.expressions[i] = new Literal(objects[i]);
        }
    }

    @Override
    public String asString() {
        String[] ex = StringUtils.asString(expressions);
        return String.join(", ", ex);
    }
}
