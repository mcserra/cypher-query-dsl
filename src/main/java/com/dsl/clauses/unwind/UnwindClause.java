package com.dsl.clauses.unwind;

import com.dsl.clauses.Alias;
import com.dsl.clauses.Clause;
import com.dsl.expressions.param.Variable;

import java.util.Collection;

public class UnwindClause implements Clause, Alias {
    private final Collection<?> array;
    private String as;
    private final Variable variable;

    private UnwindClause(Collection<?> array, String as, Variable variable) {
        this.array = array;
        this.as = as;
        this.variable = variable;
    }

    public static UnwindClause collectionUnwind(final Collection<?> array) {
        return new UnwindClause(array, null, null);
    }

    public static UnwindClause varUnwind(final Variable variable) {
        return new UnwindClause(null, null, variable);
    }

    public void setAs(final String as) {
        this.as = as;
    }

    @Override
    public String asString() {
        String s = array == null ? variable.asString() : array.toString();
        return String.format("UNWIND %s AS %s", s, as);
    }
}
