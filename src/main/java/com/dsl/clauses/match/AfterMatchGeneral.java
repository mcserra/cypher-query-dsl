package com.dsl.clauses.match;

import com.dsl.clauses.where.Where;
import com.dsl.expressions.path.PathExpression;

public interface AfterMatchGeneral extends AfterMatch, Where<AfterMatchWhere> {
    AfterMatchGeneral path(PathExpression pathExpression);
    AfterMatchGeneral path(String pathExpression);
}
