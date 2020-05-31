package com.dsl.clauses.set;

import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.SelectorExpression;

public interface SetProp {
    SetEquals prop(String o);
    SetEquals prop(Selector o);
    SetEquals prop(SelectorExpression e);
    AfterSet remove(Selector o);
    AfterSet remove(String o);
}
