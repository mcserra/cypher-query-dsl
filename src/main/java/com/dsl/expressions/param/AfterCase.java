package com.dsl.expressions.param;

import com.dsl.expressions.Expression;

public interface AfterCase {
    AfterCaseWhen when(final Expression e);

    AfterCaseWhen when(final Object s);
}
