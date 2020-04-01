package com.dsl.expressions.param;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Dsl.collect;
import static com.dsl.Dsl.select;
import static com.dsl.expressions.ExpressionBuilder.json;

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

    @Test
    void collectTest() {
        String s = collect(json("n", new Selector("y").prop("x"))).as("a").asString();
        Assertions.assertEquals("collect({n: y.x}) AS a", s);
    }

    @Test
    void collectTest2() {
        String s = collect(select("s").prop("p")).as("a").asString();
        Assertions.assertEquals("collect(s.p) AS a", s);
    }

    @Test
    void collectTest3() {
        String s = collect(new Variable("s")).as("a").asString();
        Assertions.assertEquals("collect($s) AS a", s);
    }
}
