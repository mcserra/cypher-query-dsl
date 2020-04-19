package com.dsl;

import com.dsl.clauses.ClauseBuilder;
import com.dsl.clauses.CreateClause;
import com.dsl.clauses.MatchClause;
import com.dsl.clauses.WithClause;
import com.dsl.clauses.linking.ClauseBuilderNewSyntax;
import com.dsl.clauses.links.AfterMatch;
import com.dsl.clauses.links.AfterWith;
import com.dsl.expressions.aggregating.Average;
import com.dsl.expressions.aggregating.Collect;
import com.dsl.expressions.aggregating.Count;
import com.dsl.expressions.aggregating.Max;
import com.dsl.expressions.aggregating.Min;
import com.dsl.expressions.aggregating.PercentileCont;
import com.dsl.expressions.aggregating.PercentileDisc;
import com.dsl.expressions.aggregating.StdDev;
import com.dsl.expressions.aggregating.StdDevP;
import com.dsl.expressions.aggregating.Sum;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.logical.Not;
import com.dsl.expressions.param.Date;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Json;
import com.dsl.expressions.param.Literal;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.Node;
import com.dsl.expressions.path.Path;
import com.dsl.expressions.path.PathExpression;

import java.time.LocalDate;

public class Query {

    private Query() {
    }

    public static AfterMatch create(PathExpression... expressions) {
        return new ClauseBuilder(new CreateClause(expressions));
    }

    public static AfterMatch match(PathExpression... expressions) {
        return new ClauseBuilder(new MatchClause(expressions));
    }

    public static com.dsl.clauses.linking.AfterWith with() {
        return new ClauseBuilderNewSyntax(new WithClause());
    }

    public static com.dsl.clauses.linking.AfterMatch match() {
        return new ClauseBuilderNewSyntax(new MatchClause());
    }

    public static com.dsl.clauses.linking.AfterMatch optMatch() {
        return new ClauseBuilderNewSyntax(MatchClause.optMatch());
    }

    public static AfterMatch optMatch(PathExpression... expressions) {
        return new ClauseBuilder(new MatchClause(true, expressions));
    }

    public static AfterWith with(FinalExpression... expressions) {
        return new ClauseBuilder(new WithClause(expressions));
    }

    public static Collect collect(SelectorExpression expression) {
        return new Collect(expression);
    }

    public static Average avg(SelectorExpression expression) {
        return new Average(expression);
    }

    public static Count count(SelectorExpression expression) {
        return new Count(expression);
    }

    public static Count countAll() {
        return new Count(select("*"));
    }

    public static Min min(SelectorExpression expression) {
        return new Min(expression);
    }

    public static Max max(SelectorExpression expression) {
        return new Max(expression);
    }

    public static PercentileCont percentileCont(Double percentile, SelectorExpression expression) {
        return new PercentileCont(percentile, expression);
    }

    public static PercentileDisc percentileDisc(Double percentile, SelectorExpression expression) {
        return new PercentileDisc(percentile, expression);
    }

    public static StdDev stDev(SelectorExpression expression) {
        return new StdDev(expression);
    }

    public static StdDevP stDevP(SelectorExpression expression) {
        return new StdDevP(expression);
    }

    public static Sum sum(SelectorExpression expression) {
        return new Sum(expression);
    }

    public static Date date(String s) {
        return new Date(s);
    }

    public static Date date(LocalDate date) {
        return new Date(date.toString());
    }

    public static Date date(Property s) {
        return new Date(s);
    }

    public static Variable var(String s) {
        return new Variable(s);
    }

    public static Selector select(String alias) {
        return new Selector(alias);
    }

    public static Json json(Object... literal) {
        return new Json(literal);
    }

    public static Literal literal(Object literal) {
        return new Literal(literal);
    }

    public static PathExpression node(String selector) {
        return new Path(new Node(selector, ""));
    }

    public static PathExpression node() {
        return node("");
    }

    public static Not not(LogicalExpression e) {
        return new Not(e);
    }
}
