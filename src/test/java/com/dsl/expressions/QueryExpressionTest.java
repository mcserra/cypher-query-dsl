package com.dsl.expressions;

import com.dsl.Query;
import com.dsl.expressions.path.VariablePath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.dsl.Query.*;

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
        String s = with().select(var("a").as("s")).asString();
        Assertions.assertEquals("WITH $a AS s", s);
    }

    //Node
    @Test
    void testNode() {
        String s = node("s:Series").right().rel("ht:HAS_TYPE").to("t:Type").asString();
        Assertions.assertEquals("(s:Series)-[ht:HAS_TYPE]->(t:Type)", s);
    }

    @Test
    void testNodeWithProperty() {
        String s = node("s:Series").props("foo", "bar", "kid", "boy").right().rel("ht:HAS_TYPE").to("t:Type").asString();
        Assertions.assertEquals("(s:Series {foo: bar, kid: boy})-[ht:HAS_TYPE]->(t:Type)", s);
    }

    @Test
    void testNodeWithDiffProperty() {
        String s = node("s:Series").props("foo", "bar").props("kid", "boy").right().rel("ht:HAS_TYPE").to("t:Type").asString();
        Assertions.assertEquals("(s:Series {foo: bar, kid: boy})-[ht:HAS_TYPE]->(t:Type)", s);
    }

    @Test
    void testRelationshipWithProperty() {
        String s = node("s:Series").right().rel("ht:HAS_TYPE").relProps("foo", "bar", "kid", "boy").to("t:Type").asString();
        Assertions.assertEquals("(s:Series)-[ht:HAS_TYPE {foo: bar, kid: boy}]->(t:Type)", s);
    }

    @Test
    void testAssignPathToVariable() {
        VariablePath p = node("a").right().node("b").right().node("c").assignTo("p");
        Assertions.assertEquals("p=(a)-->(b)-->(c)", p.asString());
    }

    @Test
    void collectTest() {
        String s = match(node("n:Person")).returns(collect(select("n").prop("age"))).asString();
        Assertions.assertEquals("MATCH (n:Person) RETURN collect(n.age)", s);
    }

    //Aggregating expressions
    @Test
    void avgTest() {
        String s = with().select(literal(1).as("dur")).returns(avg(select("dur"))).asString();
        Assertions.assertEquals("WITH 1 AS dur RETURN avg(dur)", s);
    }

    @Test
    void countTest() {
        String s = with().select(literal(1).as("dur")).returns(count(select("dur"))).asString();
        Assertions.assertEquals("WITH 1 AS dur RETURN count(dur)", s);
    }

    @Test
    void countAllTest() {
        String s = match(node("n").props("name", literal("A")).right().rel("r").to()).returns("r", countAll()).asString();
        Assertions.assertEquals("MATCH (n {name: 'A'})-[r]->() RETURN r, count(*)", s);
    }

    // @Test //TODO: When we have distinct
    // void countWithoutDuplicates() {
    //     String s = match(node("me", "Person"))
    //         .where(select("me").prop("name").eq(literal("A")))
    //         .returns(count()).asString();
    //     Assertions.assertEquals(
    //         "MATCH (me:Person) WHERE me.name = 'A' RETURN count(DISTINCT friend_of_friend), count(friend_of_friend)", s);
    //
    // }

    @Test
    void minTest() {
        String s = with().select(select("[1, 3]").as("x")).returns(min(select("x"))).asString();
        Assertions.assertEquals("WITH [1, 3] AS x RETURN min(x)", s);
    }

    @Test
    void maxTest() {
        String s = with().select(select("[1, 3]").as("x")).returns(max(select("x"))).asString();
        Assertions.assertEquals("WITH [1, 3] AS x RETURN max(x)", s);
    }

    @Test
    void percentileContTest() {
        String s = match(node("n:Person")).returns(percentileCont(0.4, select("n").prop("age"))).asString();
        Assertions.assertEquals("MATCH (n:Person) RETURN percentileCont(n.age, 0.4)", s);
    }

    @Test
    void percentileDiscTest() {
        String s = match(node("n:Person")).returns(percentileDisc(0.5, select("n").prop("age"))).asString();
        Assertions.assertEquals("MATCH (n:Person) RETURN percentileDisc(n.age, 0.5)", s);
    }

    @Test
    void stdDev() {
        String s = match(node("n")).where(select("n").prop("name").in("A", "B", "C")).returns(stDev(select("n").prop("age"))).asString();
        Assertions.assertEquals("MATCH (n) WHERE n.name IN ['A', 'B', 'C'] RETURN stDev(n.age)", s);
    }

    @Test
    void stdDevP() {
        String s = match(node("n")).where(select("n").prop("name").in("A", "B", "C")).returns(stDevP(select("n").prop("age"))).asString();
        Assertions.assertEquals("MATCH (n) WHERE n.name IN ['A', 'B', 'C'] RETURN stDevP(n.age)", s);
    }

    @Test
    void sumTest() {
        String s = match(node("n:Person")).returns(sum(select("n").prop("age"))).asString();
        Assertions.assertEquals("MATCH (n:Person) RETURN sum(n.age)", s);
    }

    @Test
    void inWithVar() {
        String s = match(node("n:Person")).where(select("n").in(var("persons"))).returns(sum(select("n").prop("age"))).asString();
        Assertions.assertEquals("MATCH (n:Person) WHERE n IN $persons RETURN sum(n.age)", s);
    }

    @Test
    void notTest() {
        String s = not(select("s").prop("name").eq("b")).asString();
        Assertions.assertEquals("NOT (s.name = 'b')", s);
    }

    @Test
    void optionalMatchTest() {
        String s = optMatch(node().right().node()).asString();
        Assertions.assertEquals("OPTIONAL MATCH ()-->()", s);
    }

    @Test
    void optionalMatchConditionalTest() {
        String s = optMatch(node("a").right().node()).where(select("a").eq(literal("foo"))).optMatch(node("c")).asString();
        Assertions.assertEquals("OPTIONAL MATCH (a)-->() WHERE a = 'foo' OPTIONAL MATCH (c)", s);
    }

    @Test
    void optionalMatchWithTest() {
        String s = with().select(literal("s").as("A")).optMatch(node("b")).asString();
        Assertions.assertEquals("WITH 's' AS A OPTIONAL MATCH (b)", s);
    }

    // String expressions

    @Test
    void trimTest() {
        String s = trim(literal(" hello ")).asString();
        Assertions.assertEquals("trim(' hello ')", s);
    }

    @Test
    void leftTest() {
        String s = left(literal("hello"), var("length")).asString();
        Assertions.assertEquals("left('hello', $length)", s);
    }

    @Test
    void rightTest() {
        String s = right(literal("hello"), 2).asString();
        Assertions.assertEquals("right('hello', 2)", s);
    }

    @Test
    void lTrimTest() {
        String s = lTrim(literal("  hello")).asString();
        Assertions.assertEquals("lTrim('  hello')", s);
    }

    @Test
    void rTrimTest() {
        String s = rTrim(literal("  hello")).asString();
        Assertions.assertEquals("rTrim('  hello')", s);
    }

    @Test
    void replaceTest() {
        String s = replace(literal("hello"), literal("l"), literal("w")).asString();
        Assertions.assertEquals("replace('hello', 'l', 'w')", s);
    }

    @Test
    void reverseTest() {
        String s = reverse(literal("hello")).asString();
        Assertions.assertEquals("reverse('hello')", s);
    }

    @Test
    void splitTest() {
        String s = split(literal("one,two"), literal(",")).asString();
        Assertions.assertEquals("split('one,two', ',')", s);
    }

    @Test
    void substringTest() {
        Assertions.assertEquals("substring('hello', 1, 3)", substring(literal("hello"), 1, 3).asString());
        Assertions.assertEquals("substring('hello', 1)", substring(literal("hello"), 1).asString());
    }

    @Test
    void toLowerTest() {
        Assertions.assertEquals("toLower('HELLO')", toLower(literal("HELLO")).asString());
        Assertions.assertEquals("toUpper('hello')", toUpper(literal("hello")).asString());
    }

    @Test
    void toStringTest() {
        Assertions.assertEquals("toString(a)", Query.toString("a").asString());
    }


    @Test
    void caseTest() {
        String s = match()
            .path(node("n"))
            .returns(
                caze()
                    .when(select("n.eyes").eq(literal("blue")))
                    .then(1)
                    .when(select("n.age").lt(40))
                    .then(2)
                    .els(3)
            ).as("result").asString();
        Assertions.assertEquals("MATCH (n)" +
            " RETURN" +
            " CASE" +
            " WHEN n.eyes = 'blue'" +
            " THEN 1" +
            " WHEN n.age < 40" +
            " THEN 2" +
            " ELSE 3 END AS result", s);
    }

    @Test
    void caseTestStrings() {
        String s = match()
            .path(node("n"))
            .returns(
                caze()
                    .when("n.eyes = 'blue'")
                    .then(literal(1))
                    .when("n.age < 40")
                    .then(literal(2))
                    .els(literal(3))
            ).as("result").asString();
        Assertions.assertEquals("MATCH (n)" +
            " RETURN" +
            " CASE" +
            " WHEN n.eyes = 'blue'" +
            " THEN 1" +
            " WHEN n.age < 40" +
            " THEN 2" +
            " ELSE 3 END AS result", s);
    }
}
