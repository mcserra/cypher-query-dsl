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

import static com.dsl.Query.select;

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

    // A != B
    @Test
    void not() {
        LogicalExpression a = new Not(new Literal(1).eq(2));
        Assertions.assertEquals("NOT (1 = 2)", a.asString());
    }

    @Test
    void btObject() {
        LogicalExpression a = new Literal(2).bt(1).and(new Literal(2).lt(5));
        Assertions.assertEquals("2 > 1 AND 2 < 5", a.asString());
    }

    @Test
    void bt() {
        LogicalExpression a = new Literal(2).bt(new Literal(1)).and(new Literal(2).lt(5));
        Assertions.assertEquals("2 > 1 AND 2 < 5", a.asString());
    }

    @Test
    void ltTest() {
        LogicalExpression a = new Date(new Variable("date1")).lt(new Date("2019-12-31"));
        Assertions.assertEquals("date($date1) < date('2019-12-31')", a.asString());
    }

    @Test
    void ltObjectTest() {
        LogicalExpression a = new Selector("l").prop("size").lt(1);
        Assertions.assertEquals("l.size < 1", a.asString());
    }

    @Test
    void bteTest() {
        LogicalExpression a = new Date(new Variable("date1")).bte(new Date("2019-12-31"));
        Assertions.assertEquals("date($date1) >= date('2019-12-31')", a.asString());
    }

    @Test
    void bteObjectTest() {
        LogicalExpression a = new Selector("l").prop("size").bte(1);
        Assertions.assertEquals("l.size >= 1", a.asString());
    }

    @Test
    void lteObjectTest() {
        LogicalExpression a = new Selector("l").prop("size").lte(1);
        Assertions.assertEquals("l.size <= 1", a.asString());
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
    void in3() {
        LogicalExpression a = new Literal(2).in(select("a").prop("s"));
        Assertions.assertEquals("2 IN [a.s]", a.asString());
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

    @Test
    void contains(){
        LogicalExpression a = new Literal("Peter").contains("ete");
        Assertions.assertEquals("'Peter' CONTAINS 'ete'", a.asString());
    }

    @Test
    void contains2() {
        LogicalExpression a = new Literal("Johanna").contains(new Not(new Literal("ete")));
        Assertions.assertEquals("'Johanna' CONTAINS NOT ('ete')", a.asString());
    }

}
