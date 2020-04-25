package com.dsl.clauses.linking;

import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Variable;

public interface Skip {

  AfterSkip skip(int numElements);

  AfterSkip skip(Variable variable);

  AfterSkip skip(Expression expression);
}
