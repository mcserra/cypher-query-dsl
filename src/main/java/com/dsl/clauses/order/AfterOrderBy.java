package com.dsl.clauses.order;

import com.dsl.clauses.creates.Create;
import com.dsl.clauses.limit.Limit;
import com.dsl.clauses.match.Match;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.with.With;

public interface AfterOrderBy extends Limit, With, Returns, Match, Create, Unwind {
}
