package com.dsl.clauses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LimitClauseTest {

  @Test
  void asString() {
    assertEquals("LIMIT 3", new LimitClause(3).asString());
  }
}