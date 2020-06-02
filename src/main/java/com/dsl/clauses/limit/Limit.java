package com.dsl.clauses.limit;

import com.dsl.expressions.Expression;
import com.dsl.expressions.param.Variable;

public interface Limit<T> {
    T limit(int numElements);

    T limit(Variable variable);

    T limit(Expression expression);
}
