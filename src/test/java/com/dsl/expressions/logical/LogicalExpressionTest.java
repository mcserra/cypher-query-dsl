package com.dsl.expressions.logical;

import com.dsl.expressions.param.Literal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LogicalExpressionTest {

    // A = B AND C = D
    @Test
    void and() {
        LogicalExpression a = new Literal("A").eq("B")
            .and(new Literal("C").eq(new Literal("D")));
        Assertions.assertEquals("'A' = 'B' AND 'C' = 'D'", a.asString());
    }

    // (A = B AND C = D) AND B = C
    @Test
    void andNested() {
        LogicalExpression a = new Literal("A").eq(new Literal("B"))
            .and(new Literal("C").eq(new Literal("D")))
            .and(new Literal("B").eq(new Literal("C")));
        Assertions.assertEquals("('A' = 'B' AND 'C' = 'D') AND 'B' = 'C'", a.asString());
    }

    // A = B AND (C = D AND B = C)
    @Test
    void andNested2() {
        LogicalExpression a = new Literal("A").eq(new Literal("B"))
            .and(new Literal("C").eq(new Literal("D")).and(new Literal("B").eq(new Literal("C"))));
        Assertions.assertEquals("'A' = 'B' AND ('C' = 'D' AND 'B' = 'C')", a.asString());
    }

    // A = B AND ((C = D AND B = C) OR B = A)
    @Test
    void andNested3() {
        LogicalExpression a = new Literal("A").eq(new Literal("B"))
            .and(
                new Literal("C").eq(new Literal("D"))
                    .and(new Literal("B").eq(new Literal("C")))
                    .or(new Literal("B").eq(new Literal("A")))
            );
        Assertions.assertEquals("'A' = 'B' AND (('C' = 'D' AND 'B' = 'C') OR 'B' = 'A')", a.asString());
    }

    @Test
    void or() {
        LogicalExpression a = new Literal("A").eq(new Literal("B"))
            .or(new Literal("C").eq(new Literal("D")));
        Assertions.assertEquals("'A' = 'B' OR 'C' = 'D'", a.asString());
    }

    @Test
    void not() {
        LogicalExpression a = new Not(new Literal("A").eq("B"));
        Assertions.assertEquals("NOT ('A' = 'B')", a.asString());
    }

    @Test
    void not2() {
        LogicalExpression a = new Not(new Literal("A").eq("B").and(new Literal("X").ne("Y")));
        Assertions.assertEquals("NOT ('A' = 'B' AND 'X' != 'Y')", a.asString());
    }

    @Test
    void not3() {
        LogicalExpression a = new Literal("A").ne("B").and(new Not(new Literal("A").eq("B")));
        Assertions.assertEquals("'A' != 'B' AND NOT ('A' = 'B')", a.asString());
    }

}
