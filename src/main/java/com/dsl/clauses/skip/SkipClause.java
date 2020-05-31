package com.dsl.clauses.skip;

import com.dsl.AsString;
import com.dsl.clauses.Clause;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.Variable;

public class SkipClause implements AsString, Clause {
  private final AsString argument;

  public SkipClause(final int numElementsToSkip) {
    this.argument = new Selector(Integer.toString(numElementsToSkip));
  }

  public SkipClause(final Variable variable) {
    this.argument = variable;
  }

  public SkipClause(final Expression expression) {
    this.argument = expression;
  }

  @Override
  public String asString() {
    return String.format("SKIP %s", argument.asString());
  }
}
