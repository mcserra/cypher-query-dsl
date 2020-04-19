package com.dsl.expressions.path;

import com.dsl.expressions.logical.LogicalExpression;

/**
 * In query languages, path expressions identify an object by describing how to navigate to it in some graph of objects.
 */
public interface PathExpression extends LogicalExpression {

    PathExpression node(String node);

    PathExpression node();

    RelationshipPathExpression rel(String relationship);

    RelationshipPathExpression rel();

    RelationshipPathExpression rel(String selector, String relationship);

    /**
     * Joins a relationship with a direction to the right node.
     *
     * @return RelationshipPathExpression.
     */
    PathExpression right();

    /**
     * Joins a relationship with a direction to the left node.
     *
     * @return RelationshipPathExpression.
     */
    PathExpression left();

    /**
     * Adds properties filtering properties to the PathExpression.
     *
     * @param o should be a even number of String - Object
     * @return PathExpression
     */
    PathExpression props(Object... o);
}
