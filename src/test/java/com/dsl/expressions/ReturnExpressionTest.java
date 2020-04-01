package com.dsl.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Dsl.as;
import static com.dsl.Dsl.date;
import static com.dsl.Dsl.match;
import static com.dsl.Dsl.node;
import static com.dsl.Dsl.select;

public class ReturnExpressionTest {

    @Test
    void property() {
        String s = match(
            node("a", "A")
        ).returns(
            select("a").prop("name")
        ).asString();

        Assertions.assertEquals("MATCH (a:A) RETURN a.name", s);
    }

    @Test
    void selector() {
        String s = match(
            node("a", "A")
        ).returns(
            select("a").prop("name"), select("a")
        ).asString();

        Assertions.assertEquals("MATCH (a:A) RETURN a.name, a", s);
    }

    @Test
    void alias() {
        String s = match(
            node("a", "A")
        ).returns(
            as(select("a").prop("name"), "x")
        ).asString();

        Assertions.assertEquals("MATCH (a:A) RETURN a.name AS x", s);
    }

    @Test
    void dateTest() {
        String s = match(
            node("a", "A")
        ).returns(
            as(date(select("a").prop("validFrom")), "date")
        ).asString();

        Assertions.assertEquals("MATCH (a:A) RETURN date(a.validFrom) AS date", s);
    }
}
