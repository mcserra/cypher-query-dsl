package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateClause implements Clause {
    private final List<Expression> pathExpressions = new ArrayList<>();

    public CreateClause(PathExpression... pathExpressions) {
        Collections.addAll(this.pathExpressions, pathExpressions);
    }

    public void addExpression(final PathExpression e) {
        this.pathExpressions.add(e);
    }

    public void addExpression(final String e) {
        this.pathExpressions.add(new Selector(e));
    }

    @Override
    public String asString() {
        return String.format("CREATE %s", String.join(", ", StringUtils.asString(pathExpressions)));
    }
}
