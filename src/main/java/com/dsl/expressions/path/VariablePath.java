package com.dsl.expressions.path;

import com.dsl.AsString;

public class VariablePath implements AsString {
  private final String name;
  private final PathExpression pathExpression;

  public VariablePath(String name, PathExpression pathExpression) {
    this.name = name;
    this.pathExpression = pathExpression;
  }


  @Override
  public String asString() {
    return String.format("%s=%s", name, pathExpression.asString());
  }
}
