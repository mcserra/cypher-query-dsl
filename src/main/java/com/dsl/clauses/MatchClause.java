package com.dsl.clauses;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchClause implements AsString, Clause {
    private final List<PathExpression> pathExpressions;
    private boolean optional;

    //Remove after new syntax
    public MatchClause(PathExpression... pathExpressions) {
        this.pathExpressions = new ArrayList<>(Arrays.asList(pathExpressions));
    }

    public MatchClause(final boolean optional, final PathExpression... pathExpressions) {
        this.optional = optional;
        this.pathExpressions = new ArrayList<>(Arrays.asList(pathExpressions));
    }

    public static MatchClause optMatch() {
        return new MatchClause(true);
    }

    public void addExpression(final PathExpression e) {
        this.pathExpressions.add(e);
    }

    @Override
    public String asString() {
        String opt = optional ? "OPTIONAL " : "";
        return String.format("%sMATCH %s", opt, String.join(", ", StringUtils.asString(pathExpressions)));
    }
}
