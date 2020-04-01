package com.dsl;

import com.dsl.clauses.Clause;
import com.dsl.clauses.MatchClause;
import com.dsl.clauses.WithClause;
import com.dsl.expressions.param.As;
import com.dsl.expressions.param.Collect;
import com.dsl.expressions.param.Date;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Literal;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.Node;
import com.dsl.expressions.path.Path;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Dsl {

    private Collection<Clause> clauses = new ArrayList<>();

    public static AfterMatch match(PathExpression... pathExpressions) {
        return new ClauseBuilder(new MatchClause(pathExpressions));
    }

    public static AfterMatch with(FinalExpression... pathExpressions) {
        return new ClauseBuilder(new WithClause(pathExpressions));
    }

    public static As as(SelectorExpression e, String alias) {
        return new As(alias, e);
    }

    public static Collect collect(SelectorExpression... expressions) {
        return new Collect(expressions);
    }

    public static Date date(String s) {
        return new Date(s);
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

    public static Literal literal(Object literal) {
        return new Literal(literal);
    }

    public static PathExpression node(String alias, String name) {
        return new Path(new Node(alias, name));
    }

    public Dsl(Clause clauses) {
        this.clauses.add(clauses);
    }

    //@Override
    //public String asString() {
    //    return String.join(" ", StringUtils.asString(clauses));
    //}
}
