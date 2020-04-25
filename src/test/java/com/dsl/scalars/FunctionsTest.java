package com.dsl.scalars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.node;
import static com.dsl.scalars.Functions.length;

class FunctionsTest {

  @Test
  void usePathExpressionAsParameter() {
    Assertions.assertEquals("length((n:Person))", length(node("n:Person")).asString());
  }

  @Test
  void useVariableAsParameter() {
    Assertions.assertEquals("length(p)", length(node("n:Person").assignTo("p")).asString());
  }

}