package com.dsl;

import com.dsl.expressions.path.PathExpression;

public interface Match {
    AfterMatch match(PathExpression... e);

    AfterMatch optMatch(PathExpression... e);
}
