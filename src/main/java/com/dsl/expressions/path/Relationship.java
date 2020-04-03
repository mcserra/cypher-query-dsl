package com.dsl.expressions.path;

/**
 * A graph is made up of vertices (also called nodes or points) which are connected by edges (also called links or lines).
 * This object represent an edge.
 */
public class Relationship implements PathProperty {

    private String alias;
    private String nodeName;
    private Direction dir;
    private GraphProperties properties;

    public Relationship(String alias, String nodeName, Direction direction) {
        this.alias = alias;
        this.nodeName = nodeName;
        this.dir = direction;
        this.properties = new GraphProperties();
    }

    public String getAlias() {
        return alias;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Direction getDir() {
        return dir;
    }

    public GraphProperties getProperties() {
        return properties;
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
