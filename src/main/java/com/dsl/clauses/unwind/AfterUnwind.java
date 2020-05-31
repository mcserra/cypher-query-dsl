package com.dsl.clauses.unwind;

import com.dsl.clauses.creates.Create;
import com.dsl.clauses.match.Match;
import com.dsl.clauses.merge.Merge;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.set.Set;
import com.dsl.clauses.with.With;

public interface AfterUnwind extends Match, With, Returns, Create, Merge, Unwind, Set {
}
