package com.dsl.expressions;

import com.dsl.expressions.bool.BooleanExpression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.logical.Not;
import com.dsl.expressions.param.Date;
import com.dsl.expressions.param.Literal;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.Variable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionTest {

    // A = B
    @Test
    void equals() {
        LogicalExpression a = new Literal("A").eq(new Literal("B"));
        Assertions.assertEquals("'A' = 'B'", a.asString());
    }

    // A != B
    @Test
    void notEquals() {
        LogicalExpression a = new Literal("A").ne("B");
        Assertions.assertEquals("'A' != 'B'", a.asString());
    }

    @Test
    void bt() {
        LogicalExpression a = new Literal(2).bt(1).and(new Literal(2).lt(5));
        Assertions.assertEquals("2 > 1 AND 2 < 5", a.asString());
    }

    @Test
    void in() {
        LogicalExpression a = new Literal(2).in(2, 3, 4, 5);
        Assertions.assertEquals("2 IN [2, 3, 4, 5]", a.asString());
    }

    @Test
    void in2() {
        LogicalExpression a = new Literal("A").ne("B").and(new Not(new Literal("A").in("B")));
        Assertions.assertEquals("'A' != 'B' AND NOT ('A' IN ['B'])", a.asString());
    }

    @Test
    void variables() {
        LogicalExpression a = new Variable("a").ne(new Variable("b"));
        Assertions.assertEquals("$a != $b", a.asString());
    }

    @Test
    void date() {
        LogicalExpression a = new Date("2019-01-01").lte(new Date("2019-12-31"));
        Assertions.assertEquals("date('2019-01-01') <= date('2019-12-31')", a.asString());
    }

    @Test
    void date1() {
        LogicalExpression a = new Date(new Variable("date1")).lte(new Date("2019-12-31"));
        Assertions.assertEquals("date($date1) <= date('2019-12-31')", a.asString());
    }

    @Test
    void instance() {
        LogicalExpression a = new Selector("date").eq("c");
        Assertions.assertEquals("date = 'c'", a.asString());
    }

    @Test
    void property() {
        BooleanExpression a = new Selector("date").prop("validFrom");
        Assertions.assertEquals("date.validFrom", a.asString());
    }
}
