package com.dsl.clauses.with;

import com.dsl.AsString;
import com.dsl.clauses.ClauseAlias;
import com.dsl.clauses.order.OrderBy;

/**
 * After select() can be aliased - as() - or can proceed to another clause, or another select
 */
public interface WithSelectAlias extends AsString, AfterWithSelect, ClauseAlias<AfterWithSelect>, OrderBy<AfterWithOrderBy> {
}
