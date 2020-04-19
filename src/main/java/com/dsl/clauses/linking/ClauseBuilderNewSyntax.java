package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.clauses.MatchClause;
import com.dsl.clauses.WithClause;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.List;

public class ClauseBuilderNewSyntax implements AfterMatch, AsString, AfterWith, WithAlias {

    private final List<Clause> clauses = new ArrayList<>();

    public ClauseBuilderNewSyntax(final Clause clause) {
        this.clauses.add(clause);
    }

    @Override
    public AfterMatch path(final PathExpression pathExpression) {
        getLast(MatchClause.class).addExpression(pathExpression);
        return this;
    }

    @Override
    public WithAlias select(final FinalExpression finalExpression) {
        getLast(WithClause.class).addExpression(finalExpression);
        return this;
    }

    @Override
    public WithAlias select(String expression) {
        getLast(WithClause.class).addExpression(new Selector(expression));
        return this;
    }

    @Override
    public AfterWith as(String selector) {
        getLast(WithClause.class).addSelector(selector);
        return this;
    }

    @Override
    public AfterMatch match() {
        clauses.add(new MatchClause());
        return this;
    }

    @Override
    public AfterMatch optMatch() {
        clauses.add(MatchClause.optMatch());
        return this;
    }

    @Override
    public AfterWith with() {
        clauses.add(new WithClause());
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
