package com.dsl.clauses.with;

import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.order.AfterOrderBy;
import com.dsl.clauses.skip.Skip;

public interface AfterWithOrderBy extends AfterWith, AfterOrderBy<AfterWithOrderBy>, Limit<AfterWith>, Skip<AfterWithSkip> {
}
