package com.dsl.clauses;

import com.dsl.StringUtils;
import com.dsl.expressions.param.Property;

/**
 * ORDER BY is a sub-clause following RETURN or WITH, and it specifies that the output should be sorted and how.
 */
public class OrderByClause implements Clause {
    private final Property[] properties;

    public OrderByClause(String... properties) {
        this.properties = new Property[properties.length];
        for (int i = 0; i < properties.length; i++) {
            this.properties[i] = new Property(properties[i]);
        }
    }

    public OrderByClause(Property... properties) {
        this.properties = properties;
    }

    @Override
    public String asString() {
        return String.format("ORDER BY %s", StringUtils.join(properties));
    }
}
