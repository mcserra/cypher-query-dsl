package com.dsl.expressions.path;

/**
 * A vertex or node is the fundamental unit of which graphs are formed.
 */
public class Node implements PathProperty {

    private String alias;
    private String nodeName;
    private Direction direction;
    private GraphProperties properties;

    public Node(String alias, String nodeName, Direction direction) {
        this.alias = alias;
        this.nodeName = nodeName;
        this.direction = direction;
        this.properties = new GraphProperties();
    }

    public Node(String alias, Direction direction) {
        this(alias, null, direction);
    }

    public Node(Direction direction) {
        this(null, null, direction);
    }

    public Node(String alias, String nodeName) {
        this(alias, nodeName, null);
    }

    public void addProperties(Object... props) {
        properties.add(props);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String asString() {
        StringBuilder builder = new StringBuilder();

        builder.append('(');
        if (alias != null && !alias.isBlank()) {
            builder.append(alias);
        }
        if (nodeName != null && !nodeName.isBlank()) {
            builder.append(':');
            builder.append(nodeName);
        }
        builder.append(properties.asString());
        builder.append(')');
        return builder.toString();
    }
}
