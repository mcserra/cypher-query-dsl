package com.dsl.clauses.with;

import com.dsl.AsString;
import com.dsl.clauses.creates.Create;
import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.match.Match;
import com.dsl.clauses.merge.Merge;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.set.Set;
import com.dsl.clauses.skip.Skip;
import com.dsl.clauses.unwind.Unwind;

public interface AfterWith extends Match, AsString, With, Returns, Limit, Skip, Create, Merge, Unwind, Set {
}
