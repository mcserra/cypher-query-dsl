package com.dsl.clauses.links;

import com.dsl.clauses.links.AfterLimit;
import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Variable;

public interface Limit {
    AfterLimit limit(int numElements);

    AfterLimit limit(Variable variable);

    AfterLimit limit(Expression expression);
}
