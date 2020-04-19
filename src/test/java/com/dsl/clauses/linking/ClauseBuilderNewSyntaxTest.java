package com.dsl.clauses.linking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.match;
import static com.dsl.Query.node;
import static com.dsl.Query.optMatch;
import static com.dsl.Query.select;
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
}
