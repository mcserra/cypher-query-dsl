package com.dsl.clauses.linking;

import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.PathExpression;

import java.util.Collection;

public interface ClauseImpl extends Create, Merge, Match, Returns, Where, With, Limit, Skip, OrderBy, Unwind, AfterWith {

    ClauseBuilder clauseBuilder();

    default AfterCreate create(PathExpression... pathExpressions) {
        return clauseBuilder().create(pathExpressions);
    }

    @Override
    default CreatePath create() {
        return clauseBuilder().create();
    }

    default AfterMatch match(PathExpression... pathExpressions) {
        return clauseBuilder().match(pathExpressions);
    }

    default AfterMatch optMatch(PathExpression... pathExpressions) {
        return clauseBuilder().optMatch(pathExpressions);
    }

    @Override
    default MatchPath match() {
        return clauseBuilder().match();
    }

    @Override
    default MatchPath optMatch() {
        return clauseBuilder().optMatch();
    }

    default AfterWith with() {
        return clauseBuilder().with();
    }

    default AfterWhere where(final LogicalExpression logicalExpression) {
        return clauseBuilder().where(logicalExpression);
    }

    default AfterWhere where(final String expression) {
        return clauseBuilder().where(expression);
    }

    default AfterWhere where(final PathExpression pathExpression) {
        return clauseBuilder().where(pathExpression);
    }

    default ReturnAlias returns(Expression... e) {
        return clauseBuilder().returns(e);
    }

    default AfterMerge merge(PathExpression... e) {
        return clauseBuilder().merge(e);
    }

    default ReturnAlias returns(String... e) {
        return clauseBuilder().returns(e);
    }

    default ReturnAlias returns(Object... e) {
        return clauseBuilder().returns(e);
    }

    default AfterLimit limit(int numElements) {
        return clauseBuilder().limit(numElements);
    }

    default AfterLimit limit(Variable variable) {
        return clauseBuilder().limit(variable);
    }

    default AfterLimit limit(Expression expression) {
        return clauseBuilder().limit(expression);
    }

    default AfterOrderBy orderBy(String... properties) {
        return clauseBuilder().orderBy(properties);
    }

    default AfterOrderBy orderBy(Property... properties) {
        return clauseBuilder().orderBy(properties);
    }

    default AfterSkip skip(int numElements) {
        return clauseBuilder().skip(numElements);
    }

    default AfterSkip skip(Variable variable) {
        return clauseBuilder().skip(variable);
    }

    default AfterSkip skip(Expression expression) {
        return clauseBuilder().skip(expression);
    }

    default UnwindAlias unwind(Collection<?> a) {
        return clauseBuilder().unwind(a);
    }

    default UnwindAlias unwind(Variable var) {
        return clauseBuilder().unwind(var);
    }

    default WithAlias select(final FinalExpression finalExpression) {
        return clauseBuilder().select(finalExpression);
    }

    default WithAlias select(String expression) {
        return clauseBuilder().select(expression);
    }



    @Override
    default SetProp set() {
        return clauseBuilder().set();
    }
}
