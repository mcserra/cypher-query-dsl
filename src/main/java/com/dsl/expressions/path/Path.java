package com.dsl.expressions.path;

import com.dsl.expressions.logical.ConditionalAbstractExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In graph theory, a path in a graph is a finite or infinite sequence of edges which joins a sequence of vertices.
 */
public class Path extends ConditionalAbstractExpression implements PathExpression, RelationshipPathExpression {

    private final List<PathProperty> e = new ArrayList<>();
    private Direction lastDir = Direction.NONE;

    public Path(PathProperty e) {
        this.e.add(e);
    }

    @Override
    public PathExpression to(String node) {
        e.add(new Node(node, popDirection()));
        return this;
    }

    @Override
    public PathExpression to() {
        e.add(new Node(popDirection()));
        return this;
    }

    @Override
    public PathExpression right() {
        lastDir = Direction.RIGHT;
        return this;
    }

    @Override
    public PathExpression left() {
        lastDir = Direction.LEFT;
        return this;
    }

    @Override
    public PathExpression node(String node) {
        e.add(new Node(node, popDirection()));
        return this;
    }

    @Override
    public PathExpression node() {
        e.add(new Node(popDirection()));
        return this;
    }

    @Override
    public RelationshipPathExpression rel() {
        e.add(new Relationship(popDirection()));
        return this;
    }

    @Override
    public RelationshipPathExpression rel(String rel) {
        e.add(new Relationship(rel, popDirection()));
        return this;
    }

    @Override
    public RelationshipPathExpression rel(String selector, String rel) {
        e.add(new Relationship(selector, rel, popDirection()));
        return this;
    }

    @Override
    public PathExpression props(Object... o) {
        getLast(PathProperty.class).ifPresent(pe -> pe.addProperties(o));
        return this;
    }

    @Override
    public RelationshipPathExpression relProps(Object... o) {
        getLast(PathProperty.class).ifPresent(pe -> pe.addProperties(o));
        return this;
    }

    private Direction popDirection() {
        PathProperty lastProp = getLast(PathProperty.class).orElse(null);
        Direction dir = lastProp instanceof Relationship ? null : lastDir;
        lastDir = Direction.NONE;
        return dir;
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
            return nodeString((Node) p);
        }
    }

    private String nodeString(final Node node) {
        if (node.getDirection() == null) {
            return node.asString();
        } else if (Direction.LEFT.equals(node.getDirection())) {
            return String.format("<--%s", node.asString());
        } else if (Direction.RIGHT.equals(node.getDirection())) {
            return String.format("-->%s", node.asString());
        } else {
            return String.format("--%s", node.asString());
        }
    }

    private String relationshipString(final Relationship rel) {
        if (Direction.LEFT.equals(rel.getDirection())) {
            return String.format("<-%s-", rel.asString());
        } else if (Direction.RIGHT.equals(rel.getDirection())) {
            return String.format("-%s->", rel.asString());
        } else {
            return String.format("-%s-", rel.asString());
        }
    }
}
