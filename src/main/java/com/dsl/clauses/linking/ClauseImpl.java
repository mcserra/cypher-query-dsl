package com.dsl.clauses.linking;

import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.path.PathExpression;

public abstract class ClauseImpl implements
    Create, Merge, Match, Returns, Where, With {

    protected final ClauseBuilder clauseBuilder;

    public ClauseImpl(final ClauseBuilder clauseBuilder) {
        this.clauseBuilder = clauseBuilder;
    }

    @Override
    public AfterCreate create(PathExpression... pathExpressions) {
        return this.clauseBuilder.create(pathExpressions);
    }

    @Override
    public AfterMatch match(PathExpression... pathExpressions) {
        return this.clauseBuilder.match(pathExpressions);
    }

    @Override
    public AfterMatch optMatch(PathExpression... pathExpressions) {
        return this.clauseBuilder.optMatch(pathExpressions);
    }

    @Override
    public AfterWith with() {
        return this.clauseBuilder.with();
    }

    @Override
    public AfterWhere where(final LogicalExpression logicalExpression) {
        return this.clauseBuilder.where(logicalExpression);
    }

    @Override
    public AfterWhere where(final String expression) {
        return this.clauseBuilder.where(expression);
    }

    @Override
    public AfterWhere where(final PathExpression pathExpression) {
        return this.clauseBuilder.where(pathExpression);
    }

    @Override
    public AfterReturns returns(Expression... e) {
        return this.clauseBuilder.returns(e);
    }

    @Override
    public AfterMerge merge(PathExpression... e) {
        return this.clauseBuilder.merge(e);
    }

    @Override
    public AfterReturns returns(String... e) {
        return this.clauseBuilder.returns(e);
    }

    @Override
    public AfterReturns returns(Object... e) {
        return this.clauseBuilder.returns(e);
    }
}
