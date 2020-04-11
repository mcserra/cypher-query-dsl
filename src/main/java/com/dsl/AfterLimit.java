package com.dsl;

import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.SelectorExpression;

public interface AfterLimit extends AsString {

  AsString returns(SelectorExpression... e);

  AsString returns(String... e);

  AsString returns(Object... e);

  AfterWith with(FinalExpression... e);

}
