package com.dsl.expressions.param;

import com.dsl.AsString;
import com.dsl.expressions.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Case implements Expression, AfterCaseWhen, AfterCaseThen, AfterCase, SelectorExpression {
    private final List<WhenThen> whenThen;
    private Expression elseCondition;

    public Case() {
        this.whenThen = new ArrayList<>();
        this.elseCondition = null;
    }

    @Override
    public Case end() {
        return this;
    }

    @Override
    public Case els(Object s) {
        this.elseCondition = new Selector(getString(s));
        return this;
    }

    @Override
    public Case els(Expression e) {
        this.elseCondition = e;
        return this;
    }

    @Override
    public AfterCaseWhen when(Expression e) {
        this.whenThen.add(new WhenThen(e));
        return this;
    }

    @Override
    public AfterCaseWhen when(Object s) {
        this.whenThen.add(new WhenThen(getString(s)));
        return this;
    }

    @Override
    public AfterCaseThen then(Object s) {
        this.whenThen.get(this.whenThen.size() - 1).setThen(getString(s));
        return this;
    }

    @Override
    public AfterCaseThen then(Expression e) {
        this.whenThen.get(this.whenThen.size() - 1).setThen(e);
        return this;
    }

    private String getString(final Object o) {
        return o instanceof AsString ? ((AsString) o).asString() : o.toString();
    }

    @Override
    public String asString() {
        String elseString = elseCondition == null ? "" : String.format(" ELSE %s", elseCondition.asString());
        return String.format("CASE %s%s END", whenThenAsString(), elseString);
    }

    private String whenThenAsString() {
        return whenThen.stream().map(WhenThen::asString).collect(Collectors.joining(" "));
    }

    @Override
    public FinalExpression as(String selector) {
        return this;
    }

    private static class WhenThen implements AsString {
        private final Expression when;
        private Expression then;

        public WhenThen(final Expression when) {
            this.when = when;
        }

        public WhenThen(final String when) {
            this.when = new Selector(when);
        }

        public void setThen(final Expression then) {
            this.then = then;
        }

        public void setThen(final String then) {
            this.then = new Selector(then);
        }

        @Override
        public String asString() {
            return String.format("WHEN %s THEN %s", when.asString(), then.asString());
        }
    }
}
