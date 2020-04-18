package com.dsl.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.*;

public class ClauseBuilderTests {

  // MATCH
  @Test
  void match1() {
    String s = match(
        node("s:Series").props("power", 1000, "description", "x")
            .left().rel("rel:Relation").to("a:Andes"),
        node("m:Model").props("name", "n")
    ).asString();

    Assertions.assertEquals("MATCH (s:Series {power: 1000, description: x})<-[rel:Relation]-(a:Andes), (m:Model {name: n})", s);
  }

  @Test
  void match2() {
    String s = match(
        node("s:Series").props("power", 1000, "description", "x")
            .right().rel("rel:Relation").to("a:Andes"),
        node("m:Model").props("name", "n")
    ).asString();

    Assertions.assertEquals("MATCH (s:Series {power: 1000, description: x})-[rel:Relation]->(a:Andes), (m:Model {name: n})", s);
  }

  @Test
  void innerMatch() {
    String s = with(select("a").as("b"))
        .match(node("b")).returns("b").asString();

    Assertions.assertEquals("WITH a AS b MATCH (b) RETURN b", s);
  }

  // WHERE
  @Test
  void where1() {
    String s =
        match(node("s:Series").props("power", 1000, "description", "x")
            .right().rel("rel:Relation").to("a:Andes"), node("m:Model").props("name", "n")
        ).where(select("s").prop("description").eq("X")
        ).returns(select("s")
        ).asString();

    Assertions.assertEquals("MATCH (s:Series {power: 1000, description: x})-[rel:Relation]->(a:Andes), (m:Model {name: n}) WHERE s.description = 'X' RETURN s", s);
  }

  // WITH
  @Test
  void startingWith() {
    String s = with(literal(1)).returns("A").asString();
    Assertions.assertEquals("WITH 1 RETURN A", s);
  }

  @Test
  void middleWith() {
    String s = match(node("s:Series")).with(select("s")).returns("s").asString();
    Assertions.assertEquals("MATCH (s:Series) WITH s RETURN s", s);
  }

  @Test
  void multipleWith() {
    String s = match(node("s:Series"), node("b:Models"))
        .with(select("s"), select("b"))
        .returns("b", select("s.code"))
        .asString();
    Assertions.assertEquals("MATCH (s:Series), (b:Models) WITH s, b RETURN b, s.code", s);
  }

  @Test
  void aliasedWith() {
    String s = match(node("s:Series")).with(select("s").as("A")).returns("A").asString();
    Assertions.assertEquals("MATCH (s:Series) WITH s AS A RETURN A", s);
  }

  // RETURN
  @Test
  void returnString() {
    String s = with(literal(1).as("x")).returns("x").asString();
    Assertions.assertEquals("WITH 1 AS x RETURN x", s);
  }

  @Test
  void returnStringAndExpression() {
    String s = with(literal(1).as("x")).returns("x", select("x.name").as("a")).asString();
    Assertions.assertEquals("WITH 1 AS x RETURN x, x.name AS a", s);
  }

  @Test
  void returnExpressionAliased() {
    String s = with(literal(1).as("x")).returns(select("x").as("a")).asString();
    Assertions.assertEquals("WITH 1 AS x RETURN x AS a", s);
  }

  @Test
  void returnExpressionProps() {
    String s = with(literal(1).as("x")).returns(select("x").prop("name")).asString();
    Assertions.assertEquals("WITH 1 AS x RETURN x.name", s);
  }

  @Test
  void returnsWithWrongType() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> with(literal(1).as("x")).returns(1).asString());
  }

  // LIMIT
  @Test
  void limitWithInt() {
    String s = match(node("n")).returns(select("n").prop("name")).limit(1).asString();
    Assertions.assertEquals("MATCH (n) RETURN n.name LIMIT 1", s);
  }

  @Test
  void limitWithLiteral() {
    String s = match(node("n")).returns(select("n").prop("name")).limit(literal(1)).asString();
    Assertions.assertEquals("MATCH (n) RETURN n.name LIMIT 1", s);
  }

  @Test
  void limitWithVariable() {
    String s = match(node("s")).with(select("s")).limit(var("limit")).returns("s").asString();
    Assertions.assertEquals("MATCH (s) WITH s LIMIT $limit RETURN s", s);
  }

  // CREATE
  @Test
  void createMultiplePaths() {
    String s = create(node("n:Name").props("name", "'Fred'"),
        node("s:Colour").props("colour", "'orange'")).returns(select("n").prop("name")).limit(1).asString();
    Assertions.assertEquals("CREATE (n:Name {name: 'Fred'}), (s:Colour {colour: 'orange'}) RETURN n.name LIMIT 1", s);
  }

  @Test
  void createAfterClause() {
    String s = match(node("n:Name").props("name", "'Fred'"), node("s:Colour").props("colour", "'orange'"))
        .create(node("n:Name").props("name", "'Mark'"))
        .returns(select("n").prop("name")).limit(1).asString();
    Assertions.assertEquals("MATCH (n:Name {name: 'Fred'}), (s:Colour {colour: 'orange'}) CREATE (n:Name {name: 'Mark'}) RETURN n.name LIMIT 1", s);
  }

  // SKIP
  @Test
  void skipWithInt() {
    String s = match(node("n")).returns(select("n").prop("name")).skip(1).limit(1).asString();
    Assertions.assertEquals("MATCH (n) RETURN n.name SKIP 1 LIMIT 1", s);
  }

  @Test
  void skipWithLiteral() {
    String s = match(node("n")).returns(select("n").prop("name")).skip(literal(1)).limit(literal(1)).asString();
    Assertions.assertEquals("MATCH (n) RETURN n.name SKIP 1 LIMIT 1", s);
  }

  @Test
  void skipWithVariable() {
    String s = match(node("s")).with(select("s")).skip(var("skip")).limit(var("limit")).returns("s").asString();
    Assertions.assertEquals("MATCH (s) WITH s SKIP $skip LIMIT $limit RETURN s", s);
  }
}
