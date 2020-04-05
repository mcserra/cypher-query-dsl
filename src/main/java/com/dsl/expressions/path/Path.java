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
    public PathExpression to(String alias) {
        return to(alias, null);
    }

    @Override
    public PathExpression to() {
        return to(null);
    }

    @Override
    public RelationshipPathExpression right(String alias) {
        return right(alias, null);
    }

    @Override
    public RelationshipPathExpression right() {
        return right(null);
    }

    @Override
    public RelationshipPathExpression left(String alias) {
        return left(alias, null);
    }

    @Override
    public RelationshipPathExpression left() {
        return left(null);
    }

    @Override
    public RelationshipPathExpression toRel(String alias) {
        return toRel(alias, null);
    }

    @Override
    public RelationshipPathExpression toRel() {
        return toRel(null);
    }

    @Override
    public RelationshipPathExpression right(String alias, String rel) {
        e.add(new Relationship(alias, rel, Direction.RIGHT));
        return this;
    }

    @Override
    public RelationshipPathExpression left(String alias, String rel) {
        e.add(new Relationship(alias, rel, Direction.LEFT));
        return this;
    }

    @Override
    public RelationshipPathExpression toRel(String alias, String rel) {
        e.add(new Relationship(alias, rel, Direction.NONE));
        return this;
    }

    @Override
    public PathExpression rightNode(String alias, String node) {
        e.add(new Node(alias, node, Direction.RIGHT));
        return this;
    }

    @Override
    public PathExpression leftNode(String alias, String node) {
        e.add(new Node(alias, node, Direction.LEFT));
        return this;
    }

    @Override
    public PathExpression rightNode(String alias) {
        e.add(new Node(alias, null, Direction.RIGHT));
        return this;
    }

    @Override
    public PathExpression leftNode(String alias) {
        e.add(new Node(alias, null, Direction.LEFT));
        return this;
    }

    @Override
    public PathExpression toNode(String alias, String node) {
        e.add(new Node(alias, node, Direction.NONE));
        return this;
    }

    @Override
    public PathExpression toNode(String alias) {
        e.add(new Node(alias, null, Direction.NONE));
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

    private String nodeString(Node node) {
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
        if (Direction.LEFT.equals(rel.getDir())) {
            return String.format("<-%s-", rel.asString());
        } else if (Direction.RIGHT.equals(rel.getDir())) {
            return String.format("-%s->", rel.asString());
        } else {
            return String.format("-%s-", rel.asString());
        }
    }
}
