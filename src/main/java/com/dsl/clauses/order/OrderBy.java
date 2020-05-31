package com.dsl.clauses.order;

import com.dsl.expressions.param.Property;

public interface OrderBy {
    AfterOrderBy orderBy(String... properties);

    AfterOrderBy orderBy(Property... properties);
}
