package com.dsl.clauses;

import com.dsl.AsString;
import com.dsl.expressions.param.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SetClause implements Clause {
    private final List<Set> set = new ArrayList<>();

    public Set addClause(Selector selector) {
        final Set s = new Set(selector);
        this.set.add(s);
        return s;
    }

    public SetClause setValue(Object value) {
        this.set.get(set.size() - 1).setValue(value);
        return this;
    }

    public void setOperator(String operator) {
        this.set.get(set.size() - 1).setOperator(operator);
    }

    @Override
    public String asString() {
        String s = set.stream().map(Set::asString).collect(Collectors.joining(", "));
        return String.format("SET %s", s);
    }

    public static class Set implements AsString {
        private Selector selector;
        private Object value;
        private String operator = "=";

        public Set(Selector selector) {
            this.selector = selector;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        @Override
        public String asString() {
            return String.format("%s %s %s", selector.asString(), operator, value.toString());
        }
    }
}
