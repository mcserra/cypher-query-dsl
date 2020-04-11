package com.dsl;

import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.path.PathExpression;

public interface Where {
    AfterCondition where(LogicalExpression... e);
}
