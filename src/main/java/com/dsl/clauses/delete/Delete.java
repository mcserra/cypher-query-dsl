package com.dsl.clauses.delete;

import com.dsl.expressions.param.Selector;

public interface Delete {
    AfterDelete delete(Selector selector);

    AfterDelete delete(String selector);
}
