package com.dsl;

import com.dsl.clauses.MatchClause;
import com.dsl.clauses.WithClause;
import com.dsl.expressions.param.Collect;
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

    public static AfterMatch match(PathExpression... pathExpressions) {
        return new ClauseBuilder(new MatchClause(pathExpressions));
    }

    public static AfterMatch with(FinalExpression... pathExpressions) {
        return new ClauseBuilder(new WithClause(pathExpressions));
    }

    public static Collect collect(SelectorExpression... expressions) {
        return new Collect(expressions);
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

    public static PathExpression node(String alias, String name) {
        return new Path(new Node(alias, name));
    }
}
