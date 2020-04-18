package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.path.PathExpression;

public class CreateClause implements Clause {
    private final PathExpression[] pathExpressions;

    public CreateClause(PathExpression... pathExpressions) {
        this.pathExpressions = pathExpressions;
    }

    @Override
    public String asString() {
        return String.format("CREATE %s", String.join(", ", StringUtils.asString(pathExpressions)));
    }
}
