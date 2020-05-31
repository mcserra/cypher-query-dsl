package com.dsl.clauses.skip;

import com.dsl.AsString;
import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.match.Match;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.with.With;

public interface AfterSkip extends Returns, Limit, Match, With, AsString, Unwind {
}
