package com.dsl.clauses.order;

import com.dsl.expressions.param.Property;

public interface OrderBy<T> {
    T orderBy(String... properties);

    T orderBy(Property... properties);
}
