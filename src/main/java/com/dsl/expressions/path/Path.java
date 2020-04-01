package com.dsl.expressions.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In graph theory, a path in a graph is a finite or infinite sequence of edges which joins a sequence of vertices.
 */
public class Path implements PathExpression, RelationshipPathExpression {

    private final List<PathProperty> e = new ArrayList<>();

    public Path(PathProperty e) {
        this.e.add(e);
    }

    @Override
    public PathExpression to(String alias, String node) {
        e.add(new Node(alias, node));
        return this;
    }

    @Override
    public RelationshipPathExpression right(String alias, String node) {
        e.add(new Relationship(alias, node, Direction.RIGHT));
        return this;
    }

    @Override
    public RelationshipPathExpression left(String alias, String node) {
        e.add(new Relationship(alias, node, Direction.LEFT));
        return this;
    }

    @Override
    public PathExpression property(Object... o) {
        getLast(PathProperty.class).ifPresent(pe -> pe.addProperties(o));
        return this;
    }

    private <T> Optional<T> getLast(Class<T> clazz) {
        PathProperty exp = e.get(e.size() - 1);
        return clazz.isInstance(exp) ? Optional.of((T) exp) : Optional.empty();
    }

    @Override
    public String asString() {
        StringBuilder builder = new StringBuilder();
        for (PathProperty ex : e) {
            builder.append(expressionString(ex));
        }
        return builder.toString();
    }

    private String expressionString(PathProperty p) {
        if (p instanceof Relationship) {
            return relationshipString((Relationship) p);
        } else {
            return p.asString();
        }
    }

    private String relationshipString(final Relationship rel) {
        if (Direction.LEFT.equals(rel.getDir())) {
            return String.format("<-%s-", rel.asString());
        } else if (Direction.RIGHT.equals(rel.getDir())) {
            return String.format("-%s->", rel.asString());
        } else {
            return String.format("-%s-", rel.asString());
        }
    }
}
