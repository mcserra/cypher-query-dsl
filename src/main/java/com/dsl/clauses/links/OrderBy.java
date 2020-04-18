package com.dsl.clauses.links;

import com.dsl.expressions.param.Property;

public interface OrderBy {
    AfterOrderBy orderBy(String... properties);

    AfterOrderBy orderBy(Property... properties);
}
