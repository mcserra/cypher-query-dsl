package com.dsl.clauses;

import com.dsl.clauses.creates.AfterCreate;
import com.dsl.clauses.creates.Create;
import com.dsl.clauses.creates.CreatePath;
import com.dsl.clauses.delete.AfterDelete;
import com.dsl.clauses.delete.Delete;
import com.dsl.clauses.limit.AfterLimit;
import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.match.AfterMatchGeneral;
import com.dsl.clauses.match.Match;
import com.dsl.clauses.match.MatchPath;
import com.dsl.clauses.merge.AfterMerge;
import com.dsl.clauses.merge.Merge;
import com.dsl.clauses.merge.MergePath;
import com.dsl.clauses.order.AfterOrderBy;
import com.dsl.clauses.order.OrderBy;
import com.dsl.clauses.returns.ReturnAlias;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.set.SetProp;
import com.dsl.clauses.skip.AfterSkip;
import com.dsl.clauses.skip.Skip;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.unwind.UnwindAlias;
import com.dsl.clauses.with.AfterWith;
import com.dsl.clauses.with.With;
import com.dsl.clauses.with.WithAlias;
import com.dsl.clauses.with.WithSelect;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.PathExpression;

import java.util.Collection;

public interface ClauseImpl
    extends Create, Merge, Match, Returns, With, Limit, Skip, OrderBy, Unwind, AfterWith, WithSelect, Delete {

    ClauseBuilder clauseBuilder();

    default AfterCreate create(PathExpression... pathExpressions) {
        return clauseBuilder().create(pathExpressions);
    }

    @Override
    default CreatePath create() {
        return clauseBuilder().create();
    }

    default AfterMatchGeneral match(PathExpression... pathExpressions) {
        return clauseBuilder().match(pathExpressions);
    }

    default AfterMatchGeneral optMatch(PathExpression... pathExpressions) {
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

    default WithSelect with() {
        return clauseBuilder().with();
    }

    default ReturnAlias returns(Expression... e) {
        return clauseBuilder().returns(e);
    }

    default AfterMerge merge(PathExpression... e) {
        return clauseBuilder().merge(e);
    }

    default MergePath merge() {
        return clauseBuilder().merge();
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

    default AfterDelete delete(final Selector selector) {
        return clauseBuilder().delete(selector);
    }

    default AfterDelete delete(final String s) {
        return clauseBuilder().delete(s);
    }

    @Override
    default SetProp set() {
        return clauseBuilder().set();
    }
}
