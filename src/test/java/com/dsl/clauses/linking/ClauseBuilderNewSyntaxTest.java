package com.dsl.clauses.linking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.match;
import static com.dsl.Query.node;
import static com.dsl.Query.optMatch;

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
}
