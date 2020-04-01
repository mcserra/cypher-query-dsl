package com.dsl.expressions.param;

import com.dsl.AsString;
import com.dsl.expressions.AbstractBooleanExpression;

/**
 * An input variable.
 */
public class Variable extends ParamExpression implements SelectorExpression {

    public Variable(String value) {
        super(new VariableExpression(value));
    }

    private static class VariableExpression extends AbstractBooleanExpression implements AsString {
        public final String value;

        private VariableExpression(String value) {
            this.value = value;
        }

        @Override
        public String asString() {
            return String.format("$%s", value);
        }
    }
}
