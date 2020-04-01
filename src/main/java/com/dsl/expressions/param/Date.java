package com.dsl.expressions.param;

import com.dsl.AsString;
import com.dsl.expressions.AbstractBooleanExpression;
import com.dsl.expressions.Expression;

/**
 * Represents a literal that can be part of an expresion.
 */
public class Date extends ParamExpression implements SelectorExpression {

    public Date(String value) {
        super(new DateExpression(new Literal(value)));
    }

    public Date(Variable var) {
        super(new DateExpression(var));
    }

    public Date(Property property) {
        super(new DateExpression(property));
    }

    private static class DateExpression extends AbstractBooleanExpression implements AsString {
        private Expression expression;

        private DateExpression(Expression expression) {
            this.expression = expression;
        }

        @Override
        public String asString() {
            return String.format("date(%s)", expression.asString());
        }
    }
}
