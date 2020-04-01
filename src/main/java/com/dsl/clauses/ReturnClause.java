package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;

public class ReturnClause implements Clause {
    FinalExpression[] expressions;

    public ReturnClause(FinalExpression... expressions) {
        this.expressions = expressions;
    }

    @Override
    public String asString() {
        return String.format("RETURN %s", String.join(", ", StringUtils.asString(expressions)));
    }
}
