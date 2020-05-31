package com.dsl.clauses.unwind;

import com.dsl.expressions.param.Variable;

import java.util.Collection;

public interface Unwind {
    UnwindAlias unwind(Collection<?> collection);
    UnwindAlias unwind(Variable var);
}
