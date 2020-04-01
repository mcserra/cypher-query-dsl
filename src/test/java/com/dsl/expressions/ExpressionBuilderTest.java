package com.dsl.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.expressions.ExpressionBuilder.*;

public class ExpressionBuilderTest {

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
        String s = json("s", literal("2"), "s1", 2).as("foo").asString();
        Assertions.assertEquals("{s: '2', s1: 2} AS foo", s);
    }
}
