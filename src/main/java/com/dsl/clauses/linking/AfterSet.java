package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.expressions.param.Selector;

public interface AfterSet extends AsString, With, Match, Where, Returns, Create, Merge, Unwind {
    SetEquals prop(String o);
    SetEquals prop(Selector o);
    AfterSet remove(Selector o);
    AfterSet remove(String o);
}
