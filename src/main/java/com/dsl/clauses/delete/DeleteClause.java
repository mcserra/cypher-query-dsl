package com.dsl.clauses.delete;

import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Selector;

public class DeleteClause implements Clause {
    private final Expression expression;
    private boolean detach = false;

    public DeleteClause(final Expression expression) {
        this.expression = expression;
    }

    public DeleteClause(final Object o) {
        this.expression = new Selector(StringUtils.getString(o));
    }

    public void setDetach() {
        this.detach = true;
    }

    @Override
    public String asString() {
        final String detachString = detach ? "DETACH " : "";
        return String.format("%sDELETE %s", detachString, expression.asString());
    }
}
