package com.dsl.expressions.path;

/**
 * Functions to be used when on a relationship.
 */
public interface RelationshipPathExpression {
    /**
     * Joins node.
     * @param alias the node alias.
     * @param node the node name.
     * @return PathExpression.
     */
    PathExpression to(String alias, String node);
}
