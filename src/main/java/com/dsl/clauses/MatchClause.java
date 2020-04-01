package com.dsl.clauses;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.expressions.path.PathExpression;

public class MatchClause implements AsString, Clause {
    PathExpression[] pathExpressions;

    public MatchClause(PathExpression... pathExpressions) {
        this.pathExpressions = pathExpressions;
    }

    @Override
    public String asString() {
        return String.format("MATCH %s", String.join(", ", StringUtils.asString(pathExpressions)));
    }
}
