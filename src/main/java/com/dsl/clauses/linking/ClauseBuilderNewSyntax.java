package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.clauses.LimitClause;
import com.dsl.clauses.MatchClause;
import com.dsl.clauses.OrderByClause;
import com.dsl.clauses.ReturnClause;
import com.dsl.clauses.SkipClause;
import com.dsl.clauses.WhereClause;
import com.dsl.clauses.WithClause;
import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.logical.LogicalOperator;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.List;

public class ClauseBuilderNewSyntax 
    implements AfterMatch, AsString, AfterWith, WithAlias, AfterWhere, AfterReturns, AfterLimit, AfterSkip, AfterOrderBy {

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
    public AfterMatch path(final String expression) {
        getLast(MatchClause.class).addExpression(expression);
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

    @Override
    public AfterWhere where(final LogicalExpression logicalExpression) {
        clauses.add(new WhereClause(logicalExpression));
        return this;
    }

    @Override
    public AfterWhere where(final String expression) {
        clauses.add(new WhereClause(expression));
        return this;
    }

    @Override
    public AfterWhere where(final PathExpression pathExpression) {
        clauses.add(new WhereClause(pathExpression));
        return this;
    }

    @Override
    public AfterWhere and(String expression) {
        getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.AND);
        return this;
    }

    @Override
    public AfterWhere and(LogicalExpression expression) {
        getLast(WhereClause.class).addExpression(expression, LogicalOperator.AND);
        return this;
    }

    @Override
    public AfterWhere or(String expression) {
        getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.OR);
        return this;
    }

    @Override
    public AfterWhere or(LogicalExpression expression) {
        getLast(WhereClause.class).addExpression(expression, LogicalOperator.OR);
        return this;
    }

    @Override
    public AfterWhere xor(String expression) {
        getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.XOR);
        return this;
    }

    @Override
    public AfterWhere xor(LogicalExpression expression) {
        getLast(WhereClause.class).addExpression(expression, LogicalOperator.XOR);
        return this;
    }

    @Override
    public AfterReturns returns(Expression... e) {
        clauses.add(new ReturnClause(e));
        return this;
    }

    @Override
    public AfterReturns returns(String... e) {
        Selector[] ls = new Selector[e.length];
        for (int i = 0; i < e.length; i++) {
            ls[i] = new Selector(e[i]);
        }
        this.clauses.add(new ReturnClause(ls));
        return this;
    }

    @Override
    public AfterReturns returns(Object... e) {
        Expression[] ls = new Expression[e.length];
        for (int i = 0; i < e.length; i++) {
            if (e[i] instanceof String) {
                ls[i] = new Selector((String) e[i]);
            } else if (e[i] instanceof Expression) {
                ls[i] = (Expression) e[i];
            } else {
                throw new UnsupportedOperationException();
            }
        }
        this.clauses.add(new ReturnClause(ls));
        return this;
    }

    @Override
    public AfterLimit limit(int numElements) {
        clauses.add(new LimitClause(numElements));
        return this;
    }

    @Override
    public AfterLimit limit(Variable variable) {
        clauses.add(new LimitClause(variable));
        return this;
    }

    @Override
    public AfterLimit limit(Expression expression) {
        clauses.add(new LimitClause(expression));
        return this;
    }

    @Override
    public AfterOrderBy orderBy(String... properties) {
        clauses.add(new OrderByClause(properties));
        return this;
    }

    @Override
    public AfterOrderBy orderBy(Property... properties) {
        clauses.add(new OrderByClause(properties));
        return this;
    }

    @Override
    public AfterSkip skip(int numElements) {
        clauses.add(new SkipClause(numElements));
        return this;
    }

    @Override
    public AfterSkip skip(Variable variable) {
        clauses.add(new SkipClause(variable));
        return this;
    }

    @Override
    public AfterSkip skip(Expression expression) {
        clauses.add(new SkipClause(expression));
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
