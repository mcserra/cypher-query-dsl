package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.clauses.MatchClause;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.List;

public class ClauseBuilderNewSyntax implements AfterMatch, AsString {

    private final List<Clause> clauses = new ArrayList<>();

    public ClauseBuilderNewSyntax(final Clause clause) {
        this.clauses.add(clause);
    }

    @Override
    public AfterMatch path(final PathExpression pathExpression) {
        getLast(MatchClause.class).addExpression(pathExpression);
        return this;
    }

    private <T> T getLast(final Class<T> clazz) {
        Clause exp = clauses.get(clauses.size() - 1);
        return (T) exp;
    }

    @Override
    public String asString() {
        return String.join(" ", StringUtils.asString(clauses));
    }
}
