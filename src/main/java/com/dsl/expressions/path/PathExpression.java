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
     * Joins a relationship with a direction to the right node.
     *
     * @param alias        relationship alias.
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression right(String alias);

    /**
     * Joins a relationship with a direction to the right node.
     *
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression right();

    /**
     * Joins a relationship with a direction to the left node.
     *
     * @param alias        relationship alias.
     * @param relationship relationship name.
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression left(String alias, String relationship);

    /**
     * Joins a relationship with a direction to the left node.
     *
     * @param alias        relationship alias.
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression left(String alias);


    /**
     * Joins a relationship with a direction to the left node.
     *
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression left();

    /**
     * Joins a relationship with no explicit direction.
     *
     * @param alias        relationship alias.
     * @param relationship relationship name.
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression toRel(String alias, String relationship);

    /**
     * Joins a relationship with no explicit direction.
     *
     * @param alias        relationship alias.
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression toRel(String alias);

    /**
     * Joins a relationship with no explicit direction.
     *
     * @return RelationshipPathExpression.
     */
    RelationshipPathExpression toRel();

    /**
     * Joins another node with a direction to the left node.
     *
     * @param alias node alias
     * @param node node name
     * @return PathExpression
     */
    PathExpression leftNode(String alias, String node);

    /**
     * Joins another node with a direction to the left node.
     *
     * @param alias node alias
     * @param node node name
     * @return PathExpression
     */
    PathExpression rightNode(String alias, String node);

    /**
     * Joins another node with a direction to the left node.
     *
     * @param alias node alias
     * @return PathExpression
     */
    PathExpression rightNode(String alias);

    /**
     * Joins another node with a direction to the right.
     *
     * @return PathExpression
     */
    PathExpression rightNode();

    /**
     * Joins another node with a direction to the left node.
     *
     * @param alias node alias
     * @return PathExpression
     */
    PathExpression leftNode(String alias);

    /**
     * Joins another node with a direction to the left.
     *
     * @return PathExpression
     */
    PathExpression leftNode();

    /**
     * Joins another node with a no direction.
     *
     * @param alias node alias
     * @return PathExpression
     */
    PathExpression toNode(String alias);

    /**
     * Joins another node with a no direction.
     *
     * @param alias node alias
     * @return PathExpression
     */
    PathExpression toNode(String alias, String node);

    /**
     * Adds properties filtering properties to the PathExpression.
     *
     * @param o should be a even number of String - Object
     * @return PathExpression
     */
    PathExpression props(Object... o);
}
