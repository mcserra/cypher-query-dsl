package com.dsl.clauses.returns;

import com.dsl.AsString;
import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.order.OrderBy;
import com.dsl.clauses.skip.Skip;

public interface AfterReturns extends AsString, Limit, Skip, OrderBy {
}
