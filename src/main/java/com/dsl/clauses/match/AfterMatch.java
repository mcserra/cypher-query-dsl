package com.dsl.clauses.match;

import com.dsl.AsString;
import com.dsl.clauses.creates.Create;
import com.dsl.clauses.delete.Delete;
import com.dsl.clauses.merge.Merge;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.set.Set;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.with.With;

public interface AfterMatch extends AsString, With, Match, Returns, Create, Merge, Unwind, Set, Delete {
}
