package com.dsl.clauses.limit;

import com.dsl.AsString;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.with.With;

public interface AfterLimit extends AsString, Returns, With, Unwind {
}
