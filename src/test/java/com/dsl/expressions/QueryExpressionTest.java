package com.dsl.expressions;

import java.time.LocalDate;

import static com.dsl.Query.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryExpressionTest {

    //select´

    @Test
    void selectTest() {
        String s = select("s").as("b").asString();
        Assertions.assertEquals("s AS b", s);
    }

    //literal

    @Test
    void literalTest() {
        String s = literal("s").as("b").asString();
        Assertions.assertEquals("'s' AS b", s);
    }

    @Test
    void literalTestNumber() {
        String s = literal(1).as("b").asString();
        Assertions.assertEquals("1 AS b", s);
    }

    //json

    @Test
    void jsonTest() {
        String s = json("s", literal("2")).asString();
        Assertions.assertEquals("{s: '2'}", s);
    }

    @Test
    void jsonAs() {
        String s = json("s", literal("2")).as("foo").asString();
        Assertions.assertEquals("{s: '2'} AS foo", s);
    }

    @Test
    void jsonMulti() {
        String s = json("s", literal("2"), "s1", "2").as("foo").asString();
        Assertions.assertEquals("{s: '2', s1: 2} AS foo", s);
    }

    //date

    @Test
    void dateWithProperty() {
        String s = date(select("x").prop("y")).asString();
        Assertions.assertEquals("date(x.y)", s);
    }


    @Test
    void dateWithString() {
        String s = date("2019-01-01").asString();
        Assertions.assertEquals("date('2019-01-01')", s);
    }

    @Test
    void dateWithLocalDate() {
        String s = date(LocalDate.parse("2019-01-01")).asString();
        Assertions.assertEquals("date('2019-01-01')", s);
    }

    //Var

    @Test
    void varTest() {
        String s = with(var("a").as("s")).asString();
        Assertions.assertEquals("WITH $a AS s", s);
    }

    // node

    @Test
    void testNode() {
        String s = node("s", "Series").right("ht", "HAS_TYPE").to("t", "Type").asString();
        Assertions.assertEquals("(s:Series)-[ht:HAS_TYPE]->(t:Type)", s);
    }

    @Test
    void testNodeWithProperty() {
        String s = node("s", "Series").props("foo", "bar", "kid", "boy").right("ht", "HAS_TYPE").to("t", "Type").asString();
        Assertions.assertEquals("(s:Series {foo: bar, kid: boy})-[ht:HAS_TYPE]->(t:Type)", s);
    }

    @Test
    void testNodeWithDiffProperty() {
        String s = node("s", "Series").props("foo", "bar").props("kid", "boy").right("ht", "HAS_TYPE").to("t", "Type").asString();
        Assertions.assertEquals("(s:Series {foo: bar, kid: boy})-[ht:HAS_TYPE]->(t:Type)", s);
    }

    @Test
    void testRelationshipWithProperty() {
        String s = node("s", "Series").right("ht", "HAS_TYPE").relProps("foo", "bar", "kid", "boy").to("t", "Type").asString();
        Assertions.assertEquals("(s:Series)-[ht:HAS_TYPE {foo: bar, kid: boy}]->(t:Type)", s);
    }
}
