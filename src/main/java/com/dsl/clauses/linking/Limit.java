package com.dsl.clauses.linking;

import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Variable;

public interface Limit {
    AfterLimit limit(int numElements);

    AfterLimit limit(Variable variable);

    AfterLimit limit(Expression expression);
}
