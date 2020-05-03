package com.dsl.expressions.logical;

import com.dsl.AsString;
import com.dsl.expressions.bool.AbstractBooleanExpression;
import com.dsl.expressions.AbstractExpression;
import com.dsl.expressions.Expression;

/**
 * Represents a logical expression. Like And/Or/Not.
 */
public abstract class AbstractLogicalExpression extends AbstractExpression {

    public AbstractLogicalExpression(Expression left, LogicalOperator operator, Expression right) {
        super(new LogicalExpression(left, operator, right));
    }

    public static class LogicalExpression extends AbstractBooleanExpression implements AsString {
        private final Expression left;
        private final LogicalOperator operator;
        private final Expression right;

        public LogicalExpression(Expression left, LogicalOperator operator, Expression right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        public String asString() {
            return String.format("%s %s %s",stringExpression(left), operator, stringExpression(right)).trim();
        }

        private String stringExpression(Expression c) {
            if (c == null) {
                return "";
            }
            if ((c instanceof AbstractLogicalExpression && !(c instanceof Not)) || LogicalOperator.NOT.equals(operator)) {
                return String.format("(%s)", c.asString());
            }
            return c.asString();
        }
    }
}
