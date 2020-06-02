package com.dsl.clauses.order;

import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.expressions.param.Property;

/**
 * ORDER BY is a sub-clause following RETURN or WITH, and it specifies that the output should be sorted and how.
 */
public class OrderByClause implements Clause {
    private final Property[] properties;
    private OrderByDirection orderByDirection = OrderByDirection.ASC;

    public OrderByClause(String... properties) {
        this.properties = new Property[properties.length];
        for (int i = 0; i < properties.length; i++) {
            this.properties[i] = new Property(properties[i]);
        }
    }

    public OrderByClause(Property... properties) {
        this.properties = properties;
    }

    public void setOrderByDirection(final OrderByDirection orderByDirection) {
        this.orderByDirection = orderByDirection;
    }

    @Override
    public String asString() {
        return String.format("ORDER BY %s %s", StringUtils.join(properties), orderByDirection.toString());
    }
}
