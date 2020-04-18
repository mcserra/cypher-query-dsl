package com.dsl.clauses;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.clauses.links.AfterCreate;
import com.dsl.clauses.links.AfterLimit;
import com.dsl.clauses.links.AfterMatch;
import com.dsl.clauses.links.AfterOrderBy;
import com.dsl.clauses.links.AfterReturns;
import com.dsl.clauses.links.AfterWhere;
import com.dsl.clauses.links.AfterWith;
import com.dsl.expressions.Expression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ClauseBuilder
    implements AsString, AfterMatch, AfterWith, AfterWhere, AfterLimit, AfterReturns, AfterCreate, AfterOrderBy {

  private final Collection<Clause> clauses = new ArrayList<>();

  public ClauseBuilder(Clause clause) {
    this.clauses.add(clause);
  }

  @Override
  public AfterMatch match(PathExpression... pathExpressions) {
    clauses.add(new MatchClause(pathExpressions));
    return this;
  }

  @Override
  public AfterMatch optMatch(PathExpression... pathExpressions) {
    clauses.add(new MatchClause(true, pathExpressions));
    return this;
  }

  @Override
  public AfterReturns returns(SelectorExpression... e) {
    this.clauses.add(new ReturnClause(e));
    return this;
  }

  @Override
  public AfterWhere where(LogicalExpression... e) {
    clauses.add(new WhereClause(e));
    return this;
  }

  @Override
  public AfterWith with(FinalExpression... e) {
    clauses.add(new WithClause(e));
    return this;
  }

  @Override
  public AfterLimit limit(Variable e) {
    clauses.add(new LimitClause(e));
    return this;
  }

  @Override
  public AfterLimit limit(Expression e) {
    clauses.add(new LimitClause(e));
    return this;
  }

  @Override
  public AfterLimit limit(int e) {
    clauses.add(new LimitClause(e));
    return this;
  }

  @Override
  public AfterCreate create(PathExpression... e) {
    clauses.add(new CreateClause(e));
    return this;
  }

  @Override
  public AfterOrderBy orderBy(String... properties) {
    clauses.add(new OrderByClause(properties));
    return this;
  }

  @Override
  public AfterOrderBy orderBy(Property... properties) {
    clauses.add(new OrderByClause(properties));
    return this;
  }

  @Override
  public String asString() {
    return String.join(" ", StringUtils.asString(clauses));
  }

  @Override
  public ClauseBuilder returns(String... e) {
    Objects.requireNonNull(e);
    Selector[] ls = new Selector[e.length];
    for (int i = 0; i < e.length; i++) {
      ls[i] = new Selector(e[i]);
    }
    this.clauses.add(new ReturnClause(ls));
    return this;
  }

  @Override
  public ClauseBuilder returns(Object... e) {
    Objects.requireNonNull(e);
    FinalExpression[] ls = new FinalExpression[e.length];
    for (int i = 0; i < e.length; i++) {
      if (e[i] instanceof String) {
        ls[i] = new Selector((String) e[i]);
      } else if (e[i] instanceof FinalExpression) {
        ls[i] = (FinalExpression) e[i];
      } else {
        throw new UnsupportedOperationException();
      }
    }
    this.clauses.add(new ReturnClause(ls));
    return this;
  }
}
