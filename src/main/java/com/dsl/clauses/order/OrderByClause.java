package com.dsl.clauses.order;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.clauses.Clause;
import com.dsl.expressions.param.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * ORDER BY is a sub-clause following RETURN or WITH, and it specifies that the output should be sorted and how.
 */
public class OrderByClause implements Clause {
    private final List<SortedProperty> properties;

    public OrderByClause() {
        this.properties = new ArrayList<>();
    }

    public OrderByClause(String... properties) {
        this.properties = new ArrayList<>();
        for (String property : properties) {
            this.properties.add(new SortedProperty(property));
        }
    }

    public OrderByClause(Property... properties) {
        this.properties = new ArrayList<>();
        for (Property property : properties) {
            this.properties.add(new SortedProperty(property));
        }
    }

    public void setOrderByDirection(final OrderByDirection orderByDirection) {
        this.properties.get(properties.size() - 1).setOrderByDirection(orderByDirection);
    }

    public void addProperty(Property property) {
        this.properties.add(new SortedProperty(property));
    }

    public void addProperty(String property) {
        this.properties.add(new SortedProperty(property));
    }

    @Override
    public String asString() {
        return String.format("ORDER BY %s", StringUtils.join(properties));
    }

    private static class SortedProperty implements AsString {
        private final Property properties;
        private OrderByDirection orderByDirection = OrderByDirection.ASC;

        public SortedProperty(Property properties) {
            this.properties = properties;
        }

        public SortedProperty(String properties) {
            this.properties = new Property(properties);
        }

        public void setOrderByDirection(OrderByDirection orderByDirection) {
            this.orderByDirection = orderByDirection;
        }

        @Override
        public String asString() {
            return String.format("%s %s", properties.asString(), orderByDirection.toString());
        }
    }
}
