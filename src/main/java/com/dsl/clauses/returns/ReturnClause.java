package com.dsl.clauses.returns;

import com.dsl.StringUtils;
import com.dsl.clauses.Alias;
import com.dsl.clauses.Clause;
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
        return String.format("RETURN %s%s", StringUtils.join(expressions), asClause);
    }
}
