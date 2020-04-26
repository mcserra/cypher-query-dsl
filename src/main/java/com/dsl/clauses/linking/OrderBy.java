package com.dsl.clauses.linking;

import com.dsl.expressions.param.Property;

public interface OrderBy {
    AfterOrderBy orderBy(String... properties);

    AfterOrderBy orderBy(Property... properties);
}
