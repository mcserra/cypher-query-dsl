package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.logical.LogicalOperator;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WhereClause implements Clause {
    private final List<Expression> expressions = new ArrayList<>();

    public WhereClause(final LogicalExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    public WhereClause(final PathExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    public WhereClause(final String expressions) {
        this.expressions.add(new Selector(expressions));
    }

    public void addExpression(final LogicalExpression logicalExpression, final LogicalOperator logicalOperator) {
        Expression e = null;
        if (logicalOperator.equals(LogicalOperator.AND)) {
            e = getLast(LogicalExpression.class).and(logicalExpression);
        } else if (logicalOperator.equals(LogicalOperator.OR)) {
            e = getLast(LogicalExpression.class).or(logicalExpression);
        } else if (logicalOperator.equals(LogicalOperator.XOR)) {
            e = getLast(LogicalExpression.class).xor(logicalExpression);
        }
        this.expressions.set(this.expressions.size() - 1, e);
    }

    private <T> T getLast(final Class<T> clazz) {
        Expression exp = expressions.get(expressions.size() - 1);
        return (T) exp;
    }

    @Override
    public String asString() {
        return "WHERE " + String.join(", ", StringUtils.asString(expressions));
    }
}
