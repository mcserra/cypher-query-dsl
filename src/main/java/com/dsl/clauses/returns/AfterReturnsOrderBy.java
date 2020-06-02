package com.dsl.clauses.returns;

import com.dsl.AsString;
import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.order.AfterOrderBy;
import com.dsl.clauses.skip.Skip;

public interface AfterReturnsOrderBy extends AfterReturns, AfterOrderBy<AfterReturnsOrderBy>, Limit<AsString>, Skip<AfterReturnsSkip> {
}
