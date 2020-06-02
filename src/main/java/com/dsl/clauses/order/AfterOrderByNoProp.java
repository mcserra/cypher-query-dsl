package com.dsl.clauses.order;

import com.dsl.expressions.param.Property;

public interface AfterOrderByNoProp<T> {
    T prop(String s);

    T prop(Property p);
}
