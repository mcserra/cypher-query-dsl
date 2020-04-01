package com.dsl.expressions.param;

import com.dsl.AsString;
import com.dsl.expressions.AbstractBooleanExpression;

public class LiteralString extends ParamExpression implements SelectorExpression {

    public LiteralString(String value) {
        super(new StringExpression(value));
    }

    private static class StringExpression extends AbstractBooleanExpression implements AsString {
        public final String value;

        private StringExpression(String value) {
            this.value = value;
        }

        @Override
        public String asString() {
            return value;
        }
    }
}
