package com.dsl.clauses.order;

import com.dsl.expressions.param.Property;

public interface AfterOrderBy<T> {
    T prop(String s);

    T prop(Property p);

    T asc();

    T desc();
}
