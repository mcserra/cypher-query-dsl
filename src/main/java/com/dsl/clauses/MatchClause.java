package com.dsl.clauses;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchClause implements AsString, Clause {
    private final List<Expression> pathExpressions = new ArrayList<>();
    private boolean optional;

    //Remove after new syntax
    public MatchClause(PathExpression... pathExpressions) {
        Collections.addAll(this.pathExpressions, pathExpressions);
    }

    public MatchClause(final boolean optional, final PathExpression... pathExpressions) {
        this.optional = optional;
        Collections.addAll(this.pathExpressions, pathExpressions);
    }

    public static MatchClause optMatch() {
        return new MatchClause(true);
    }

    public void addExpression(final PathExpression e) {
        this.pathExpressions.add(e);
    }

    public void addExpression(final String e) {
        this.pathExpressions.add(new Selector(e));
    }

    @Override
    public String asString() {
        String opt = optional ? "OPTIONAL " : "";
        return String.format("%sMATCH %s", opt, String.join(", ", StringUtils.asString(pathExpressions)));
    }
}
