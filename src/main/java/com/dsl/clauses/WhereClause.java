package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.logical.LogicalExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WhereClause implements Clause {
    private final List<LogicalExpression> expressions = new ArrayList<>();

    public WhereClause(final LogicalExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    @Override
    public String asString() {
        return "WHERE " + String.join(", ", StringUtils.asString(expressions));
    }
}
