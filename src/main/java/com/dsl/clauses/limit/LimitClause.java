package com.dsl.clauses.limit;

import com.dsl.AsString;
import com.dsl.clauses.Clause;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.Variable;

public class LimitClause implements AsString, Clause {
  private final AsString argument;

  public LimitClause(final int numElements) {
    this.argument = new Selector(Integer.toString(numElements));
  }

  public LimitClause(final Variable variable) {
    this.argument = variable;
  }

  public LimitClause(final Expression expression) {
    this.argument = expression;
  }

  @Override
  public String asString() {
    return String.format("LIMIT %s", argument.asString());
  }
}
