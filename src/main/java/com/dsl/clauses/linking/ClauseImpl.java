package com.dsl.clauses.linking;

import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.path.PathExpression;

public interface ClauseImpl extends Create, Merge, Match, Returns, Where, With {

     ClauseBuilder clauseBuilder();

    @Override
    default AfterCreate create(PathExpression... pathExpressions) {
        return clauseBuilder().create(pathExpressions);
    }

    @Override
    default AfterMatch match(PathExpression... pathExpressions) {
        return clauseBuilder().match(pathExpressions);
    }

    @Override
    default AfterMatch optMatch(PathExpression... pathExpressions) {
        return clauseBuilder().optMatch(pathExpressions);
    }

    @Override
    default AfterWith with() {
        return clauseBuilder().with();
    }

    @Override
    default AfterWhere where(final LogicalExpression logicalExpression) {
        return clauseBuilder().where(logicalExpression);
    }

    @Override
    default AfterWhere where(final String expression) {
        return clauseBuilder().where(expression);
    }

    @Override
    default AfterWhere where(final PathExpression pathExpression) {
        return clauseBuilder().where(pathExpression);
    }

    @Override
    default AfterReturns returns(Expression... e) {
        return clauseBuilder().returns(e);
    }

    @Override
    default AfterMerge merge(PathExpression... e) {
        return clauseBuilder().merge(e);
    }

    @Override
    default AfterReturns returns(String... e) {
        return clauseBuilder().returns(e);
    }

    @Override
    default AfterReturns returns(Object... e) {
        return clauseBuilder().returns(e);
    }
}
