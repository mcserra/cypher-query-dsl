package com.dsl.clauses.set;

import com.dsl.AsString;
import com.dsl.clauses.creates.Create;
import com.dsl.clauses.match.Match;
import com.dsl.clauses.merge.Merge;
import com.dsl.clauses.returns.Returns;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.with.With;
import com.dsl.expressions.param.Selector;

public interface AfterSet extends AsString, With, Match, Returns, Create, Merge, Unwind {
    SetEquals prop(String o);
    SetEquals prop(Selector o);
    AfterSet remove(Selector o);
    AfterSet remove(String o);
}
