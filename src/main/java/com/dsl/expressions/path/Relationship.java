package com.dsl.expressions.path;

/**
 * A graph is made up of vertices (also called nodes or points) which are connected by edges (also called links or lines).
 * This object represent an edge.
 */
public class Relationship implements PathProperty {

    private String alias;
    private String nodeName;
    private Direction direction;
    private GraphProperties properties;

    public Relationship(String alias, String nodeName, Direction direction) {
        this.alias = alias;
        this.nodeName = nodeName;
        this.direction = direction;
        this.properties = new GraphProperties();
    }

    public Relationship(Direction direction) {
        this(null, null, direction);
    }

    public Relationship(String alias, Direction direction) {
        this(alias, null, direction);
    }

    public Direction getDirection() {
        return direction;
    }

    public void addProperties(Object... props) {
        properties.add(props);
    }

    @Override
    public String asString() {
        StringBuilder builder = new StringBuilder();

        builder.append('[');
        if (alias != null && !alias.isBlank()) {
            builder.append(alias);
        }
        if (nodeName != null && !nodeName.isBlank()) {
            builder.append(':');
            builder.append(nodeName);
        }
        builder.append(properties.asString());
        builder.append(']');
        return builder.toString();
    }
}
