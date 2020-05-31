package com.dsl.clauses.set;

import com.dsl.expressions.Expression;

public interface SetEquals {
    AfterSet setEq(Object o);

    AfterSet setEq(Expression e);

    AfterSet mut(Object... o);
}
