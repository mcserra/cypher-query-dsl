package com.dsl.clauses.linking;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.*;

class ClauseBuilderTest {

    //Match
    @Test
    void testMatchStart() {
        String query = match()
            .path(node("n:Name"))
            .path(node("p:Person").right().node("n"))
            .match(node("n"))
            .asString();

        Assertions.assertEquals("MATCH (n:Name), (p:Person)-->(n) MATCH (n)", query);
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
                .select("n.code").as("code")
                .asString();

        Assertions.assertEquals("MATCH (n:Name) WITH n.name AS name, n.code AS code", query);
    }

    @Test
    void withStartTest() {
        String query =
            with()
                .select(select("9"))
                .select(select("k")).as("code")
                .optMatch()
                .path(node("n"))
                .match()
                .path(node("k"))
                .asString();

        Assertions.assertEquals("WITH 9, k AS code OPTIONAL MATCH (n) MATCH (k)", query);
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

    //returns
    @Test
    void returnString() {
        String s = with().select(literal(1).as("x")).returns("x").asString();
        Assertions.assertEquals("WITH 1 AS x RETURN x", s);
    }

    @Test
    void returnStringAndExpression() {
        String s = with().select(literal(1).as("x")).returns("x", select("x.name").as("a")).asString();
        Assertions.assertEquals("WITH 1 AS x RETURN x, x.name AS a", s);
    }

    @Test
    void returnExpressionAliased() {
        String s = with().select(literal(1).as("x")).returns(select("x").as("a")).asString();
        Assertions.assertEquals("WITH 1 AS x RETURN x AS a", s);
    }

    @Test
    void returnExpressionProps() {
        String s = with().select(literal(1).as("x")).returns(select("x").prop("name")).asString();
        Assertions.assertEquals("WITH 1 AS x RETURN x.name", s);
    }

    @Test
    void returnsWithWrongType() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> with().select(literal(1).as("x")).returns(1).asString());
    }

    @Test
    void createMultiplePaths() {
        String s = create()
            .path(node("n:Name").props("name", "'Fred'"))
            .path("(s:Colour {colour: 'orange'})")
            .returns(select("n").prop("name")).limit(1).asString();
        Assertions.assertEquals("CREATE (n:Name {name: 'Fred'}), (s:Colour {colour: 'orange'}) RETURN n.name LIMIT 1", s);
    }

    @Test
    void createSinglePath() {
        String s = create(node("n:Name").props("name", "'Fred'"))
            .returns(select("n").prop("name")).limit(1).asString();
        Assertions.assertEquals("CREATE (n:Name {name: 'Fred'}) RETURN n.name LIMIT 1", s);
    }

    @Test
    void createAfterClause() {
        String s = match(node("n:Name").props("name", "'Fred'"), node("s:Colour").props("colour", "'orange'"))
            .optMatch(node("c:Country"))
            .create(node("n:Name").props("name", "'Mark'")).asString();
        Assertions.assertEquals("MATCH (n:Name {name: 'Fred'}), (s:Colour {colour: 'orange'}) OPTIONAL MATCH (c:Country) CREATE (n:Name {name: 'Mark'})", s);
    }

    @Test
    void createAfterClauseWithPath() {
        String s = match(node("n:Name").props("name", "'Fred'"), node("s:Colour").props("colour", "'orange'"))
            .optMatch(node("c:Country"))
            .create().path(node("n:Name").props("name", "'Mark'")).asString();
        Assertions.assertEquals("MATCH (n:Name {name: 'Fred'}), (s:Colour {colour: 'orange'}) OPTIONAL MATCH (c:Country) CREATE (n:Name {name: 'Mark'})", s);
    }

    @Test
    void mergeTest() {
        String s =
            merge(node("n:Name").props("name", "'Fred'"))
                .path("(s:Person {name: 'Foo'})")
                .path(node("name:Name").props("name", "'Bar'"))
                .merge(node("name1:Name").props("name", "'Carson'")).asString();
        Assertions.assertEquals("" +
            "MERGE (n:Name {name: 'Fred'}), (s:Person {name: 'Foo'}), (name:Name {name: 'Bar'})"
            + " MERGE (name1:Name {name: 'Carson'})", s);
    }

    @Test
    void mergeWithPathsTest() {
        String s =
            merge()
                .path("(s:Person {name: 'Foo'})")
                .path(node("name:Name").props("name", "'Bar'"))
            .merge()
                .path(node("name1:Name").props("name", "'Carson'"))
                .asString();
        Assertions.assertEquals("" +
            "MERGE (s:Person {name: 'Foo'}), (name:Name {name: 'Bar'})"
            + " MERGE (name1:Name {name: 'Carson'})", s);
    }

    @Test
    void onCreateWithExpression() {
        String s = merge(node("n:Name").props("name", "'Fred'"))
            .onCreate(select("n.updateDate").set(var("date")))
            .onMatch(select("n.createDate").set(var("date"))).asString();
        Assertions.assertEquals(
            "MERGE (n:Name {name: 'Fred'}) ON MATCH SET n.createDate = $date ON CREATE SET n.updateDate = $date", s);
    }

    @Test
    void onCreateWithString() {
        String s = merge(node("n:Name").props("name", "'Fred'"))
            .onCreate("n.updateDate = $date")
            .onMatch("n.createDate = $date").asString();
        Assertions.assertEquals(
            "MERGE (n:Name {name: 'Fred'}) ON MATCH SET n.createDate = $date ON CREATE SET n.updateDate = $date", s);
    }

    @Test
    void unwindTest() {
        String s = unwind(var("events")).as("event")
            .merge((node("y:Year").props("year", "event.year")))
            .merge(node("y").left().rel(":IN").to("e:Event").props("id", "event.id"))
            .returns("e.id")
            .orderBy("x").asString();

        Assertions.assertEquals("UNWIND $events AS event MERGE (y:Year {year: event.year}) " +
            "MERGE (y)<-[:IN]-(e:Event {id: event.id}) " +
            "RETURN e.id " +
            "ORDER BY x", s);
    }

    @Test
    void unwindTestWithCollection() {
        String s = unwind(List.of("a", "b", "c")).as("event")
            .merge((node("y:Year").props("year", "event.year")))
            .merge(node("y").left().rel(":IN").to("e:Event").props("id", "event.id"))
            .returns("e.id")
            .orderBy("x").asString();

        Assertions.assertEquals("UNWIND [a, b, c] AS event MERGE (y:Year {year: event.year}) " +
            "MERGE (y)<-[:IN]-(e:Event {id: event.id}) " +
            "RETURN e.id " +
            "ORDER BY x", s);
    }

    @Test
    void unwindTestWithSubCollection() {
        String s = unwind(List.of("a", List.of("d", "e"), "c")).as("event")
            .merge((node("y:Year").props("year", "event.year")))
            .merge(node("y").left().rel(":IN").to("e:Event").props("id", "event.id"))
            .returns("e.id").as("x")
            .orderBy("x").asString();

        Assertions.assertEquals("UNWIND [a, [d, e], c] AS event MERGE (y:Year {year: event.year}) " +
            "MERGE (y)<-[:IN]-(e:Event {id: event.id}) " +
            "RETURN e.id AS x " +
            "ORDER BY x", s);
    }

    @Test
    void unwindFromClauseTestWithSubCollection() {
        String s = match(node("s"))
            .unwind(List.of("a", List.of("d", "e"), "c")).as("event")
            .merge((node("y:Year").props("year", "event.year")))
            .merge(node("y").left().rel(":IN").to("e:Event").props("id", "event.id"))
            .returns("e.id").as("x")
            .orderBy("x").asString();

        Assertions.assertEquals("MATCH (s) UNWIND [a, [d, e], c] AS event MERGE (y:Year {year: event.year}) " +
            "MERGE (y)<-[:IN]-(e:Event {id: event.id}) " +
            "RETURN e.id AS x " +
            "ORDER BY x", s);
    }

    @Test
    void unwindFromClauseTest() {
        String s = match().path(node("s"))
            .unwind(var("events")).as("event")
            .merge((node("y:Year").props("year", "event.year")))
            .merge(node("y").left().rel(":IN").to("e:Event").props("id", "event.id"))
            .returns("e.id")
            .orderBy("x").asString();

        Assertions.assertEquals("MATCH (s) UNWIND $events AS event MERGE (y:Year {year: event.year}) " +
            "MERGE (y)<-[:IN]-(e:Event {id: event.id}) " +
            "RETURN e.id " +
            "ORDER BY x", s);
    }

    @Test
    void testSetProperty() {
        String s = match().path(node("n").props("name", literal("Andy")))
            .set().prop(select("n.surname")).setEq(literal("Taylor"))
            .returns("n.name", "n.surname")
            .asString();

        Assertions.assertEquals("MATCH (n {name: 'Andy'})" +
            " SET n.surname = 'Taylor'" +
            " RETURN n.name, n.surname", s);
    }

    @Test
    void testUpdateProperty() {
        String s = match().path(node("n").props("name", literal("Andy")))
            .set()
                .prop(select("n.age")).setEq(asString(1))
                .prop("n.age").setEq(asString(select("n.age")))
            .returns("n.name", "n.age")
            .asString();

        Assertions.assertEquals("MATCH (n {name: 'Andy'})" +
            " SET n.age = toString(1), n.age = toString(n.age)" +
            " RETURN n.name, n.age", s);
    }

    @Test
    void removeProperty() {
        String s = match().path(node("n").props("name", literal("Andy")))
            .set().remove(select("n.age"))
            .returns("n.name", "n.age")
            .asString();

        Assertions.assertEquals("MATCH (n {name: 'Andy'})" +
            " SET n.age = NULL" +
            " RETURN n.name, n.age", s);
    }

    @Test
    void removePropertyString() {
        String s = match().path(node("n").props("name", literal("Andy")))
            .set().remove("n.age")
            .returns("n.name", "n.age")
            .asString();

        Assertions.assertEquals("MATCH (n {name: 'Andy'})" +
            " SET n.age = NULL" +
            " RETURN n.name, n.age", s);
    }

    @Test
    void replaceAll() {
        String s = match().path(node("p").props("name", literal("Andy")))
            .set().prop(select("p")).setEq(json("name", "'Peter Smith'", "position", "'Entrepreneur'"))
            .returns("n.name", "n.age")
            .asString();

        Assertions.assertEquals("MATCH (p {name: 'Andy'})" +
            " SET p = {name: 'Peter Smith', position: 'Entrepreneur'}" +
            " RETURN n.name, n.age", s);
    }

    @Test
    void removeAll() {
        String s = match().path(node("p").props("name", literal("Andy")))
            .set().prop("p").setEq(json())
            .returns("n.name", "n.age")
            .asString();

        Assertions.assertEquals("MATCH (p {name: 'Andy'})" +
            " SET p = {}" +
            " RETURN n.name, n.age", s);
    }

    @Test
    void mutate() {
        String s = match().path(node("p").props("name", literal("Andy")))
            .set().prop("p").mut("age", 38, "hungry", true, "position", "'Entrepreneur'")
            .returns("n.name", "n.age")
            .asString();

        Assertions.assertEquals("MATCH (p {name: 'Andy'})" +
            " SET p += {age: 38, hungry: true, position: 'Entrepreneur'}" +
            " RETURN n.name, n.age", s);
    }
}
