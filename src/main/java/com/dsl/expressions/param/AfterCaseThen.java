package com.dsl.expressions.param;

import com.dsl.expressions.Expression;

public interface AfterCaseThen extends AfterCase {
    Case els(final Object s);

    Case els(final Expression e);

    Case end();
}
