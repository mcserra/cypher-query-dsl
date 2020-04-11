package com.dsl.expressions.path;

import com.dsl.expressions.Expression;

/**
 * Functions to be used when on a relationship.
 */
public interface RelationshipPathExpression extends Expression {
    /**
     * Joins node.
     *
     * @return PathExpression.
     */
    PathExpression to(String node);

    PathExpression to();

    /**
     * Adds properties filtering properties to the RelationshipPathExpression.
     *
     * @param o should be a even number of String - Object
     * @return RelationshipPathExpression
     */
    RelationshipPathExpression relProps(Object... o);
}
