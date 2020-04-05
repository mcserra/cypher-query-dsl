package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.param.FinalExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WithClause implements Clause {
    private final List<FinalExpression> expressions = new ArrayList<>();

    public WithClause(final FinalExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    @Override
    public String asString() {
        return "WITH " + String.join(", ", StringUtils.asString(expressions));
    }
}
