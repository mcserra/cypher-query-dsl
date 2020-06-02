package com.dsl.clauses.skip;

import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Variable;

public interface Skip<T> {

  T skip(int numElements);

  T skip(Variable variable);

  T skip(Expression expression);
}
