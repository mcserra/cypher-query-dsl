package com.dsl.clauses.creates;

import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.clauses.PathExpressionClause;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateClause implements Clause, PathExpressionClause {
    private final List<Expression> expressions = new ArrayList<>();

    public CreateClause(PathExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    public void addExpression(final PathExpression e) {
        this.expressions.add(e);
    }

    public void addExpression(final String e) {
        this.expressions.add(new Selector(e));
    }

    @Override
    public String asString() {
        return String.format("CREATE %s", String.join(", ", StringUtils.asString(expressions)));
    }
}
