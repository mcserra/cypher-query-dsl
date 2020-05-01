package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.AbstractExpression;
import com.dsl.expressions.Expression;
import com.dsl.expressions.bool.EqualityExpression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeClause implements Clause, PathExpressionClause {
    private final List<Expression> expressions = new ArrayList<>();
    private final Set<EqualityExpression> onMatch = new HashSet<>();
    private final Set<EqualityExpression> onCreate = new HashSet<>();

    //Remove after new syntax
    public MergeClause(PathExpression... expressions) {
        Collections.addAll(this.expressions, expressions);
    }

    public void addExpression(final PathExpression e) {
        this.expressions.add(e);
    }

    public void addExpression(final String e) {
        this.expressions.add(new Selector(e));
    }

    public void addOnMatch(final EqualityExpression... e) {
        Collections.addAll(onMatch, e);
    }

    public void addOnCreate(final EqualityExpression... e) {
        Collections.addAll(onCreate, e);
    }

    public void addOnMatch(final String... e) {
        onMatch.addAll(Stream.of(e).map(s -> new AbstractExpression(new Selector(s))).collect(Collectors.toSet()));
    }

    public void addOnCreate(final String... e) {
        onCreate.addAll(Stream.of(e).map(s -> new AbstractExpression(new Selector(s))).collect(Collectors.toSet()));
    }

    @Override
    public String asString() {
        final String onMatchClause = String.join(", ", StringUtils.asString(onMatch));
        final String onCreateClause = String.join(", ", StringUtils.asString(onCreate));
        return String.format("MERGE %s%s%s",
            String.join(", ", StringUtils.asString(expressions)),
            onMatch.isEmpty() ? "" : String.format("%s %s", " ON MATCH SET", onMatchClause),
            onCreate.isEmpty() ? "" : String.format("%s %s", " ON CREATE SET", onCreateClause));
    }
}
