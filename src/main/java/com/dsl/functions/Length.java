package com.dsl.functions;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;

public class Length implements AsString {

  private final String s;

  public Length(final String s) {
    this.s = s;
  }

  public Length(final PathExpression e) {
    this.s = e.asString();
  }

  @Override
  public String asString() {
    return String.format("length(%s)", s);
  }
}
