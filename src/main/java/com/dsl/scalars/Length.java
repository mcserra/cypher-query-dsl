package com.dsl.scalars;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;
import com.dsl.variables.Variable;

public class Length implements AsString {

  private final String argument;

  public Length(PathExpression expression) {
    this.argument = expression.asString();
  }

  public Length(Variable<PathExpression> v) {
    this.argument = v.getName();
  }

  @Override
  public String asString() {
    return String.format("length(%s)", this.argument);
  }
}
