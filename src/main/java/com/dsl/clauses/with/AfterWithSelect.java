package com.dsl.clauses.with;

import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.order.OrderBy;
import com.dsl.clauses.skip.Skip;

/**
 * After a select is made, another can be made or can pass to another clause.
 */
public interface AfterWithSelect
    extends AfterWith, WithSelect, Limit<AfterWith>, Skip<AfterWithSkip>, OrderBy<AfterWithOrderBy> {
}
