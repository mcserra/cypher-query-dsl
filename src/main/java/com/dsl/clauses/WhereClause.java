package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.logical.LogicalExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhereClause implements Clause {
    private final List<LogicalExpression> expressions = new ArrayList<>();

    public WhereClause(final Collection<LogicalExpression> expressions) {
        this.expressions.addAll(expressions);
    }

    public WhereClause(final LogicalExpression... expressions) {
        for (LogicalExpression e: expressions) {
            this.expressions.add(e);
        }
    }
    
    public void addExpression(final LogicalExpression expression) {
        this.expressions.add(expression);
    }

    @Override
    public String asString() {
        return "WHERE " + String.join(", ", StringUtils.asString(expressions));
    }
}
