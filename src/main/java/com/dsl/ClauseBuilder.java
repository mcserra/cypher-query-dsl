package com.dsl;

import com.dsl.clauses.Clause;
import com.dsl.clauses.MatchClause;
import com.dsl.clauses.ReturnClause;
import com.dsl.clauses.WhereClause;
import com.dsl.clauses.WithClause;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.LiteralString;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ClauseBuilder implements AsString, AfterMatch, AfterWith, AfterCondition {

    private Collection<Clause> clauses = new ArrayList<>();

    public ClauseBuilder(Collection<Clause> clauses) {
        this.clauses = clauses;
    }

    public ClauseBuilder(Clause clause) {
        this.clauses.add(clause);
    }

    @Override
    public AfterMatch matches(PathExpression... pathExpressions) {
        clauses.add(new MatchClause(pathExpressions));
        return this;
    }

    @Override
    public ClauseBuilder returns(SelectorExpression... e) {
        this.clauses.add(new ReturnClause(e));
        return this;
    }

    @Override
    public AfterCondition where(LogicalExpression... e) {
        clauses.add(new WhereClause(e));
        return this;
    }

    @Override
    public ClauseBuilder returns(FinalExpression... e) {
        clauses.add(new ReturnClause(e));
        return this;
    }

    @Override
    public AfterWith with(FinalExpression... e) {
        clauses.add(new WithClause(e));
        return this;
    }

    @Override
    public String asString() {
        return String.join(" ", StringUtils.asString(clauses));
    }

    @Override
    public ClauseBuilder returns(String... e) {
        Objects.requireNonNull(e);
        LiteralString[] ls = new LiteralString[e.length];
        for (int i = 0; i < e.length; i++) {
            ls[i] = new LiteralString(e[i]);
        }
        this.clauses.add(new ReturnClause(ls));
        return this;
    }

    @Override
    public ClauseBuilder returns(Object... e) {
        Objects.requireNonNull(e);
        FinalExpression[] ls = new FinalExpression[e.length];
        for (int i = 0; i < e.length; i++) {
            if (e[i] instanceof String) {
                ls[i] = new LiteralString((String) e[i]);
            } else if (e[i] instanceof FinalExpression) {
                ls[i] = (FinalExpression) e[i];
            } else {
                throw new UnsupportedOperationException();
            }
        }
        this.clauses.add(new ReturnClause(ls));
        return this;
    }
}
