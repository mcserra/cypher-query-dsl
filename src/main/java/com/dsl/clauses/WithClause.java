package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WithClause implements Clause, Alias {
    private final List<FinalExpression> expressions = new ArrayList<>();

    public WithClause(final FinalExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    public void addExpression(final FinalExpression finalExpression) {
        expressions.add(finalExpression);
    }

    private <T> T getLast(final Class<T> clazz) {
        FinalExpression exp = expressions.get(expressions.size() - 1);
        return (T) exp;
    }

    @Override
    public void setAs(String selector) {
        SelectorExpression s = getLast(SelectorExpression.class);
        expressions.set(expressions.size() - 1, s.as(selector));
    }

    @Override
    public String asString() {
        return "WITH " + String.join(", ", StringUtils.asString(expressions));
    }
}
