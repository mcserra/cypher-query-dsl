package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.Expression;

public class ReturnClause implements Clause, Alias {
    private final Expression[] expressions;
    private String as;

    public ReturnClause(Expression... expressions) {
        this.expressions = expressions;
    }

    @Override
    public void setAs(String selector) {
        this.as = selector;
    }

    @Override
    public String asString() {
        String asClause = as == null ? "" : String.format(" AS %s", as);
        return String.format("RETURN %s%s", String.join(", ", StringUtils.asString(expressions)), asClause);
    }
}
