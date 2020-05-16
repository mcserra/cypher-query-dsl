package com.dsl.clauses.linking;

import com.dsl.expressions.param.Selector;

public interface SetProp {
    SetEquals prop(String o);
    SetEquals prop(Selector o);
    AfterSet remove(Selector o);
    AfterSet remove(String o);
}
