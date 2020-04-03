package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.param.FinalExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WithClause implements Clause {
    private final List<FinalExpression> expressions = new ArrayList<>();

    public WithClause(final Collection<FinalExpression> expressions) {
        this.expressions.addAll(expressions);
    }

    public WithClause(final FinalExpression... expressions) {
        for (FinalExpression e: expressions) {
            this.expressions.add(e);
        }
    }

    public void addExpression(final FinalExpression expression) {
        this.expressions.add(expression);
    }

    @Override
    public String asString() {
        return "WITH " + String.join(", ", StringUtils.asString(expressions));
    }
}
