package com.dsl.clauses.match;

import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.clauses.PathExpressionClause;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchClause implements Clause, PathExpressionClause {
    private final List<Expression> expressions = new ArrayList<>();
    private boolean optional;

    //Remove after new syntax
    public MatchClause(PathExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    public MatchClause(final boolean optional, final PathExpression... expressions) {
        this.optional = optional;
        Collections.addAll(this.expressions, expressions);
    }

    public static MatchClause optMatch(final PathExpression... pathExpression) {
        return new MatchClause(true, pathExpression);
    }

    public void addExpression(final PathExpression e) {
        this.expressions.add(e);
    }

    public void addExpression(final String e) {
        this.expressions.add(new Selector(e));
    }

    @Override
    public String asString() {
        String opt = optional ? "OPTIONAL " : "";
        return String.format("%sMATCH %s", opt, String.join(", ", StringUtils.asString(expressions)));
    }
}
