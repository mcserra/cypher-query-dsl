package com.dsl;

import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Variable;

public interface AfterReturns extends AsString {

  AsString limit(int numElements);

  AsString limit(Variable variable);

  AsString limit(Expression expression);
}
