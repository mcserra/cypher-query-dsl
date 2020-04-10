package com.dsl;

import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;

public interface AfterMatch extends AsString {
  AfterWith with(FinalExpression... e);

  AfterCondition where(LogicalExpression... e);

  AfterReturns returns(SelectorExpression... e);

  AfterReturns returns(String... e);

  AfterReturns returns(Object... e);
}
