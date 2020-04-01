package com.dsl.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Dsl.*;

public class DslTest {

    @Test
    void match1() {
        String s = match(
            node("s", "Series").property("power", 1000, "description", "x")
                .left("rel", "Relation").to("a", "Andes"),
            node("m", "Model").property("name", "n")
        ).asString();

        Assertions.assertEquals("MATCH (s:Series {description: x, power: 1000})<-[rel:Relation]-(a:Andes), (m:Model {name: n})", s);
    }

    @Test
    void match2() {
        String s = match(
            node("s", "Series").property("power", 1000, "description", "x")
                .right("rel", "Relation").to("a", "Andes"),
            node("m", "Model").property("name", "n")
        ).asString();

        Assertions.assertEquals("MATCH (s:Series {description: x, power: 1000})-[rel:Relation]->(a:Andes), (m:Model {name: n})", s);
    }

    @Test
    void where1() {
        String s =
            match(node("s", "Series").property("power", 1000, "description", "x")
                .right("rel", "Relation").to("a", "Andes"), node("m", "Model").property("name", "n")
            ).where(select("s").prop("description").eq("X")
            ).returns(select("s")
            ).asString();

        Assertions.assertEquals("MATCH (s:Series {description: x, power: 1000})-[rel:Relation]->(a:Andes), (m:Model {name: n}) WHERE s.description = 'X' RETURN s", s);
    }

    @Test
    void with1() {
        String s = with(literal(1).as("A")).returns("A").asString();
        Assertions.assertEquals("WITH 1 AS A RETURN A", s);
    }

    @Test
    void with2() {
        String s = match(node("s", "Series")).with(select("s").as("a")).returns("a").asString();
        Assertions.assertEquals("MATCH (s:Series) WITH s AS a RETURN a", s);
    }

    @Test
    void with3() {
        String s = match(node("s", "Series"), node("b", "Models"))
            .with(select("s"), select("b").as("a"))
            .returns("a", select("s.code"))
            .asString();
        Assertions.assertEquals("MATCH (s:Series), (b:Models) WITH s, b AS a RETURN a, s.code", s);
    }
}
