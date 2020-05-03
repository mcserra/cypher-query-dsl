package com.dsl.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.node;
import static com.dsl.functions.ScalarFunctions.length;

class ScalarFunctionsTest {

  // Length
  @Test
  void testLengthWhenPassAPathExpression() {
    Assertions.assertEquals("length((n:Person))", length(node("n:Person")).asString());
  }

  @Test
  void testLengthWhenPassAString() {
    Assertions.assertEquals("length(p)", length("p").asString());
  }

}