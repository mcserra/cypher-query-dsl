package com.dsl.clauses.linking;

import com.dsl.expressions.param.Selector;

public interface Delete {
    AfterDelete delete(Selector selector);

    AfterDelete delete(String selector);
}
