package com.dsl.clauses.linking;

import com.dsl.clauses.UnwindClause;
import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.PathExpression;

import java.util.Collection;

public interface ClauseImpl extends Create, Merge, Match, Returns, Where, With, Limit, Skip, OrderBy, Unwind {

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

    @Override
    default AfterLimit limit(int numElements) {
        return clauseBuilder().limit(numElements);
    }

    @Override
    default AfterLimit limit(Variable variable) {
        return clauseBuilder().limit(variable);
    }

    @Override
    default AfterLimit limit(Expression expression) {
        return clauseBuilder().limit(expression);
    }

    @Override
    default AfterOrderBy orderBy(String... properties) {
        return clauseBuilder().orderBy(properties);
    }

    @Override
    default AfterOrderBy orderBy(Property... properties) {
        return clauseBuilder().orderBy(properties);
    }

    @Override
    default AfterSkip skip(int numElements) {
        return clauseBuilder().skip(numElements);
    }

    @Override
    default AfterSkip skip(Variable variable) {
        return clauseBuilder().skip(variable);
    }

    @Override
    default AfterSkip skip(Expression expression) {
        return clauseBuilder().skip(expression);
    }

    @Override
    default UnwindAlias unwind(Collection<?> a) {
        return clauseBuilder().unwind(a);
    }

    @Override
    default UnwindAlias unwind(Variable var) {
        return clauseBuilder().unwind(var);
    }
}
