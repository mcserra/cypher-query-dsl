package com.dsl.clauses.creates;

import com.dsl.clauses.match.Match;
import com.dsl.clauses.merge.Merge;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.set.Set;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.with.With;
import com.dsl.expressions.path.PathExpression;

public interface AfterCreate extends With, Match, Returns, Create, Merge, Unwind, Set {
    AfterCreate path(PathExpression pathExpression);
    AfterCreate path(String pathExpression);
}
