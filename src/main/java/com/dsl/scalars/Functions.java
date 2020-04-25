package com.dsl.scalars;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;
import com.dsl.variables.Variable;

public class Functions {

  public static AsString length(Variable<PathExpression> v) {
    return new Length(v);
  }

  public static AsString length(PathExpression e) {
    return new Length(e);
  }
}
