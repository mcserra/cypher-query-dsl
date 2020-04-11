package com.dsl.expressions.path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.node;

public class PathExpressionTest {


    @Test
    void pathWithTwoNodesRight() {
        String s = node("s", "Name").right().node("b").right().node("c:Person").asString();
        Assertions.assertEquals("(s:Name)-->(b)-->(c:Person)", s);
    }

    @Test
    void pathWithTwoNodesLeft() {
        String s = node("s:Name").left().node("b").left().node("c:Person").asString();
        Assertions.assertEquals("(s:Name)<--(b)<--(c:Person)", s);
    }

    @Test
    void pathWithTwoNodes() {
        String s = node("s:Name").node("b").node("c:Person").asString();
        Assertions.assertEquals("(s:Name)--(b)--(c:Person)", s);
    }

    @Test
    void pathWithRelationship() {
        String s = node("s:Name").rel().to().asString();
        Assertions.assertEquals("(s:Name)-[]-()", s);
    }

    @Test
    void pathWithAliasRelationship() {
        String s = node("s:Name").rel("a").to().asString();
        Assertions.assertEquals("(s:Name)-[a]-()", s);
    }

    @Test
    void pathWithAliasRelationshipRight() {
        String s = node("s:Name").right().rel("a").to().asString();
        Assertions.assertEquals("(s:Name)-[a]->()", s);
    }

    @Test
    void pathWithAliasRelationshipLeft() {
        String s = node("s:Name").left().rel("a", "A").to().asString();
        Assertions.assertEquals("(s:Name)<-[a:A]-()", s);
    }

    @Test
    void pathRelationshipRight() {
        String s = node("s:Name").right().rel().to().asString();
        Assertions.assertEquals("(s:Name)-[]->()", s);
    }

    @Test
    void pathRelationshipLeft() {
        String s = node("s:Name").left().rel().to().asString();
        Assertions.assertEquals("(s:Name)<-[]-()", s);
    }

    @Test
    void nodeToNodeLeft() {
        String s = node("s:Name").left().node().asString();
        Assertions.assertEquals("(s:Name)<--()", s);
    }

    @Test
    void nodeToNodeRight() {
        String s = node("s:Name").right().node().asString();
        Assertions.assertEquals("(s:Name)-->()", s);
    }
}
