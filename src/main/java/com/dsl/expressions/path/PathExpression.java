package com.dsl.expressions.path;

import com.dsl.expressions.Expression;

/**
 * In query languages, path expressions identify an object by describing how to navigate to it in some graph of objects.
 */
public interface PathExpression extends Expression {
    /**
     * Joins a relationship with a direction to the right node.
     *
     * @param alias        relationship alias.
     * @param relationship relationship name.
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression right(String alias, String relationship);

    /**
     * Joins a relationship with a direction to the left node.
     *
     * @param alias        relationship alias.
     * @param relationship relationship name.
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression left(String alias, String relationship);

    /**
     * Adds properties filtering properties to the PathExpression.
     *
     * @param o should be a even number of String - Object
     * @return PathExpression
     */
    PathExpression property(Object... o);
}
