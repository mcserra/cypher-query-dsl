package com.dsl.expressions.path;

/**
 * A vertex or node is the fundamental unit of which graphs are formed.
 */
public class Node implements PathProperty {

    private String alias;
    private String nodeName;
    private GraphProperties properties;

    public Node(String alias, String nodeName) {
        this.alias = alias;
        this.nodeName = nodeName;
        this.properties = new GraphProperties();
    }

    public void addProperties(Object... props) {
        properties.add(props);
    }

    @Override
    public String asString() {
        StringBuilder builder = new StringBuilder();

        builder.append('(');
        if (alias != null) {
            builder.append(alias);
        }
        if (nodeName != null) {
            builder.append(':');
            builder.append(nodeName);
        }
        builder.append(properties.asString());
        builder.append(')');
        return builder.toString();
    }
}
