package com.dsl.variables;

import com.dsl.expressions.path.PathExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.dsl.Query.node;

public class VariableTest {
  @Test
  void testAssignPathToVariable() {
    Variable<PathExpression> p = node("a").right().node("b").right().node("c").assignTo("p");
    Assertions.assertEquals("p=(a)-->(b)-->(c)", p.asString());
  }
}
