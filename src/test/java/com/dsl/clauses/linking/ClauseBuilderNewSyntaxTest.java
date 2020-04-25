package com.dsl.clauses.linking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.literal;
import static com.dsl.Query.match;
import static com.dsl.Query.node;
import static com.dsl.Query.not;
import static com.dsl.Query.optMatch;
import static com.dsl.Query.select;
import static com.dsl.Query.var;
import static com.dsl.Query.with;

class ClauseBuilderNewSyntaxTest {

    //Match
    @Test
    void testMatchStart() {
        String query = match()
            .path(node("n:Name"))
            .path(node("p:Person").right().node("n"))
            .asString();

        Assertions.assertEquals("MATCH (n:Name), (p:Person)-->(n)", query);
    }

    @Test
    void optMatchStart() {
        String query = optMatch()
            .path(node("n:Name"))
            .path(node("p:Person").right().node("n"))
            .asString();

        Assertions.assertEquals("OPTIONAL MATCH (n:Name), (p:Person)-->(n)", query);
    }

    @Test
    void withTest() {
        String query =
            match()
                .path(node("n:Name"))
                .with()
                .select("n.name").as("name")
                .asString();

        Assertions.assertEquals("MATCH (n:Name) WITH n.name AS name", query);
    }

    @Test
    void withStartTest() {
        String query =
            with()
                .select(select("9"))
                .optMatch()
                .path(node("n"))
                .match()
                .path(node("k"))
                .asString();

        Assertions.assertEquals("WITH 9 OPTIONAL MATCH (n) MATCH (k)", query);
    }

    @Test
    void whereTest() {
        String query =
            match()
                .path(node("s"))
                .where(select("s.name").eq(literal("Foo")))
                .and("s.code in ['Foo', 'Bar']")
                .asString();

        Assertions.assertEquals("MATCH (s) WHERE s.name = 'Foo' AND s.code in ['Foo', 'Bar']", query);
    }

    @Test
    void whereDoubleAndTest() {
        String query =
            match()
                .path(node("s"))
                .where(select("s.name").eq(literal("Foo")))
                .and(select("s.age").in(10, 20).and("s.code in ['Foo', 'Bar']"))
                .or(select("s.address = 'Lisbon'").or(node("s").props("name", "'Foo'")))
                .asString();

        Assertions.assertEquals("MATCH (s) WHERE (s.name = 'Foo'" +
            " AND (s.age IN [10, 20] AND s.code in ['Foo', 'Bar']))" +
            " OR (s.address = 'Lisbon' OR (s {name: 'Foo'}))", query);
    }

    @Test
    void testWhereWithXor() {
        String query =
            match()
                .path(node("n:Person"))
            .where(select("n.name").eq(literal("Peter")))
                .xor(select("n.age").lt(30).and(select("n.name").eq("Timothy")))
                .or(not(select("n.name").eq(literal("Timothy")).or("n.name = 'Peter'")))
            .asString();
        Assertions.assertEquals("MATCH (n:Person)" +
                " WHERE (n.name = 'Peter' XOR (n.age < 30 AND n.name = 'Timothy')) OR NOT (n.name = 'Timothy' OR n.name = 'Peter')"
            , query);
    }

    @Test
    void testWhereWithXorString() {
        String query =
            match()
                .path(node("n:Person"))
                .where("n.name = 'Peter'")
                .xor("(n.age < 30 AND n.name = 'Timothy')")
                .or(not("n.name = 'Timothy' OR n.name = 'Peter'"))
                .asString();
        Assertions.assertEquals("MATCH (n:Person)" +
                " WHERE (n.name = 'Peter' XOR (n.age < 30 AND n.name = 'Timothy')) OR NOT (n.name = 'Timothy' OR n.name = 'Peter')"
            , query);
    }

    @Test
    void testWhereWithPathExpression() {
        String query =
            match()
                .path(node("n:Person"))
                .path("(s:Country)")
            .where(node("n").right().node("s"))
                .or("n.name = 'Timothy'")
                .or(select("n.name").eq("Sandra").xor("n.age < 30"))
            .asString();
        Assertions.assertEquals(
            "MATCH (n:Person), (s:Country) WHERE ((n)-->(s) OR n.name = 'Timothy')" +
                " OR (n.name = 'Sandra' XOR n.age < 30)", query);
    }

    @Test
    void returnsTest() {
        String query =
            match()
                .path(node("s"))
                .where(select("s.name").eq(literal("Foo")))
                .and("s.code in ['Foo', 'Bar']")
                .returns("s")
                .asString();

        Assertions.assertEquals("MATCH (s) WHERE s.name = 'Foo' AND s.code in ['Foo', 'Bar'] RETURN s", query);
    }

    @Test
    void returnsNodeProperty() {
        String query =
            match()
                .path(node("n").props("name", "'A'"))
                .returns("n.name")
                .asString();

        Assertions.assertEquals("MATCH (n {name: 'A'}) RETURN n.name", query);
    }

    @Test
    void returnsWithAlias() {
        String query =
            match()
                .path(node("n").props("name", "'A'"))
                .returns(select("n").prop("name").as("something"))
                .asString();

        Assertions.assertEquals("MATCH (n {name: 'A'}) RETURN n.name AS something", query);
    }

    @Test
    void returnsOtherExpressions() {
        String query =
            match()
                .path(node("a").props("name", "'A'"))
                .returns(select("a").prop("age").bt(30), "\"I'm a literal\"", node("a").right().node())
                .asString();

        Assertions.assertEquals("MATCH (a {name: 'A'}) RETURN a.age > 30, \"I'm a literal\", (a)-->()", query);
    }

    //OrderBy
    @Test
    void orderByTest() {
        String s = match()
            .path(node("n:Name").props("name", "'Fred'"))
            .with()
            .select("n")
            .orderBy("n.name")
            .returns(select("n").prop("name")).limit(1).asString();
        Assertions.assertEquals("MATCH (n:Name {name: 'Fred'}) WITH n ORDER BY n.name RETURN n.name LIMIT 1", s);
    }

    @Test
    void orderByWithSelectorTest() {
        String s = match()
            .path(node("n:Name").props("name", "'Fred'"))
            .with()
            .select(select("n"))
            .orderBy(select("n").prop("name"))
            .returns(select("n").prop("name")).orderBy("n.name").limit(1).asString();
        Assertions.assertEquals("MATCH (n:Name {name: 'Fred'}) WITH n ORDER BY n.name RETURN n.name ORDER BY n.name LIMIT 1", s);
    }

    // SKIP
    @Test
    void skipWithInt() {
        String s = match()
            .path(node("n"))
            .returns(select("n").prop("name"))
            .skip(1)
            .limit(1)
            .asString();
        Assertions.assertEquals("MATCH (n) RETURN n.name SKIP 1 LIMIT 1", s);
    }

    @Test
    void skipWithLiteral() {
        String s =
            match()
                .path(node("n"))
            .returns(select("n").prop("name"))
                .skip(literal(1))
                .limit(literal(1)).asString();
        Assertions.assertEquals("MATCH (n) RETURN n.name SKIP 1 LIMIT 1", s);
    }

    @Test
    void skipWithVariable() {
        String s = match()
            .path(node("s"))
            .with()
            .select(select("s"))
            .skip(var("skip")).limit(var("limit")).returns("s").asString();
        Assertions.assertEquals("MATCH (s) WITH s SKIP $skip LIMIT $limit RETURN s", s);
    }

    // LIMIT
    @Test
    void limitWithInt() {
        String s = match()
            .path(node("n"))
            .returns(select("n").prop("name")).limit(1).asString();
        Assertions.assertEquals("MATCH (n) RETURN n.name LIMIT 1", s);
    }

    @Test
    void limitWithLiteral() {
        String s = match()
            .path(node("n")).returns(select("n").prop("name")).limit(literal(1)).asString();
        Assertions.assertEquals("MATCH (n) RETURN n.name LIMIT 1", s);
    }

    @Test
    void limitWithVariable() {
        String s = match()
            .path(node("s")).with().select("s").limit(var("limit")).returns("s").asString();
        Assertions.assertEquals("MATCH (s) WITH s LIMIT $limit RETURN s", s);
    }
}
