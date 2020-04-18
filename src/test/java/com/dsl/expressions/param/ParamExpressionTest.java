package com.dsl.expressions.param;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParamExpressionTest {

    @Test
    void asTest() {
        String s = new Selector("n").prop("x").as("a").asString();
        Assertions.assertEquals("n.x AS a", s);
    }

    @Test
    void jsonTest() {
        String s = new Json("n", new Selector("y").prop("x")).as("a").asString();
        Assertions.assertEquals("{n: y.x} AS a", s);
    }
}
