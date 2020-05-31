package com.dsl;

import com.dsl.clauses.ClauseBuilder;
import com.dsl.clauses.creates.CreateClause;
import com.dsl.clauses.creates.AfterCreate;
import com.dsl.clauses.creates.CreatePath;
import com.dsl.clauses.match.AfterMatchGeneral;
import com.dsl.clauses.match.MatchClause;
import com.dsl.clauses.match.MatchPath;
import com.dsl.clauses.merge.AfterMerge;
import com.dsl.clauses.merge.MergeClause;
import com.dsl.clauses.merge.MergePath;
import com.dsl.clauses.unwind.UnwindAlias;
import com.dsl.clauses.unwind.UnwindClause;
import com.dsl.clauses.with.AfterWith;
import com.dsl.clauses.with.WithClause;
import com.dsl.clauses.with.WithSelect;
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
import com.dsl.expressions.param.AfterCase;
import com.dsl.expressions.param.Case;
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
import com.dsl.expressions.string.LTrim;
import com.dsl.expressions.string.Left;
import com.dsl.expressions.string.RTrim;
import com.dsl.expressions.string.Replace;
import com.dsl.expressions.string.Reverse;
import com.dsl.expressions.string.Right;
import com.dsl.expressions.string.Split;
import com.dsl.expressions.string.StringFunction;
import com.dsl.expressions.string.Substring;
import com.dsl.expressions.string.ToLower;
import com.dsl.expressions.string.ToString;
import com.dsl.expressions.string.ToUpper;
import com.dsl.expressions.string.Trim;

import java.time.LocalDate;
import java.util.Collection;

public class Query {

    private Query() {
    }

    public static UnwindAlias unwind(Collection<?> collection) {
        return new ClauseBuilder().unwind(UnwindClause.collectionUnwind(collection));
    }

    public static UnwindAlias unwind(Variable var) {
        return new ClauseBuilder().unwind((UnwindClause.varUnwind(var)));
    }

    public static AfterWith with(FinalExpression... finalExpression) {
        return new ClauseBuilder(new WithClause(finalExpression));
    }

    public static WithSelect with() {
        return new ClauseBuilder(new WithClause());
    }

    public static AfterMatchGeneral match(PathExpression... pathExpression) {
        return new ClauseBuilder().match(new MatchClause(pathExpression));
    }

    public static AfterMatchGeneral optMatch(final PathExpression... pathExpression) {
        return new ClauseBuilder().match(MatchClause.optMatch(pathExpression));
    }

    public static MatchPath match() {
        return new ClauseBuilder().match(new MatchClause());
    }

    public static MatchPath optMatch() {
        return new ClauseBuilder().match(MatchClause.optMatch());
    }

    public static AfterCreate create(PathExpression... pathExpression) {
        return new ClauseBuilder().create(new CreateClause(pathExpression));
    }

    public static CreatePath create() {
        return new ClauseBuilder().create(new CreateClause());
    }

    public static AfterMerge merge(PathExpression... pathExpression) {
        return new ClauseBuilder().merge(new MergeClause(pathExpression));
    }

    public static MergePath merge() {
        return new ClauseBuilder().merge(new MergeClause());
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

    public static Selector select(SelectorExpression selectorExpression) {
        return new Selector(selectorExpression);
    }

    public static Json json(Object... literal) {
        return new Json(literal);
    }

    public static Literal literal(Object literal) {
        return new Literal(literal);
    }

    public static String asString(Object o) {
        return new ToString(o).asString();
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

    public static Not not(String e) {
        return new Not(e);
    }

    public static StringFunction trim(Object original) {
        return new Trim(original);
    }

    public static StringFunction toUpper(Object original) {
        return new ToUpper(original);
    }

    public static StringFunction toLower(Object o) {
        return new ToLower(o);
    }

    public static StringFunction toString(Object original) {
        return new ToString(original);
    }

    public static StringFunction substring(Object original, Object start, Object length) {
        return new Substring(original, start, length);
    }

    public static StringFunction substring(Object original, Object start) {
        return new Substring(original, start);
    }

    public static StringFunction split(Object original, Object splitDelimiter) {
        return new Split(original,  splitDelimiter);
    }

    public static StringFunction rTrim(Object original) {
        return new RTrim(original);
    }

    public static StringFunction lTrim(Object original) {
        return new LTrim(original);
    }

    public static StringFunction right(Object original, Object length) {
        return new Right(original, length);
    }

    public static StringFunction left(Object original, Object length) {
        return new Left(original, length);
    }

    public static StringFunction reverse(Object original) {
        return new Reverse(original);
    }

    public static StringFunction replace(Object original, Object search, Object replace) {
        return new Replace(original, search, replace);
    }

    public static AfterCase caze() {
        return new Case();
    }

}
