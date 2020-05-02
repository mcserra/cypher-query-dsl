package com.dsl.expressions;

import com.dsl.Query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.match;
import static com.dsl.Query.node;
import static com.dsl.Query.select;

class AbstractBooleanExpressionTest {

    @Test
    void testMatchRegex() {
        String s = match(node("n:Person"))
            .where(select("n.email").matchRegex(".*\\\\.com"))
            .returns("n.name, n.age, n.email").asString();
        Assertions.assertEquals("MATCH (n:Person) WHERE n.email =~ '.*\\\\.com' RETURN n.name, n.age, n.email", s);
    }
}
