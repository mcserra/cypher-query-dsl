package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.Expression;

public class ReturnClause implements Clause {
    Expression[] expressions;

    public ReturnClause(Expression... expressions) {
        this.expressions = expressions;
    }

    @Override
    public String asString() {
        return String.format("RETURN %s", String.join(", ", StringUtils.asString(expressions)));
    }
}
