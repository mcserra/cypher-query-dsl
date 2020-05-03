package com.dsl.functions;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;

public class ScalarFunctions {

  public static AsString length(final String s) {
    return new Length(s);
  }

  public static AsString length(PathExpression e) {
    return new Length(e);
  }
}
