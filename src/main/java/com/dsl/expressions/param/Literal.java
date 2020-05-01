package com.dsl.expressions.param;

import com.dsl.AsString;
import com.dsl.expressions.bool.AbstractBooleanExpression;

/**
 * Represents a literal.
 */
public class Literal extends ParamExpression implements SelectorExpression {

    public Literal(Object value) {
        super(new LiteralExpression(value));
    }

    private static class LiteralExpression extends AbstractBooleanExpression implements AsString {
        public final Object value;

        private LiteralExpression(Object value) {
            this.value = value;
        }

        @Override
        public String asString() {
            if (value instanceof String) {
                return String.format("'%s'", value);
            }
            return value.toString();
        }
    }
}
