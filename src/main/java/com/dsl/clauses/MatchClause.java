package com.dsl.clauses;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.expressions.path.PathExpression;

public class MatchClause implements AsString, Clause {
    private final PathExpression[] pathExpressions;
    private boolean optional;

    public MatchClause(PathExpression... pathExpressions) {
        this.pathExpressions = pathExpressions;
    }

    public MatchClause(final boolean optional, final PathExpression... pathExpressions) {
        this.optional = optional;
        this.pathExpressions = pathExpressions;
    }

    @Override
    public String asString() {
        String opt = optional ? "OPTIONAL " : "";
        return String.format("%sMATCH %s", opt, String.join(", ", StringUtils.asString(pathExpressions)));
    }
}
