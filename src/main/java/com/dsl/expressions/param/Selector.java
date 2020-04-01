package com.dsl.expressions.param;

import com.dsl.AsString;
import com.dsl.expressions.AbstractBooleanExpression;
import com.dsl.expressions.Expression;

/**
 * Reference to a selected element in a query.
 */
public class Selector extends ParamExpression
    implements AsString, IdentifierExpression, SelectorExpression, Expression {

    public Selector(String selector) {
        super(new PropertyExpression(selector));
    }

    public Property prop(String property) {
        return new Property(new Selector(property), this);
    }

    @Override
    public String asString() {
        return left.asString();
    }

    public static class PropertyExpression extends ParamExpression implements SelectorExpression {

        public PropertyExpression(String s) {
            super(new StringExpressionImpl(s));
        }

        private static class StringExpressionImpl extends AbstractBooleanExpression implements AsString {
            public final String value;

            private StringExpressionImpl(String value) {
                this.value = value;
            }

            @Override
            public String asString() {
                return value;
            }
        }
    }
}
