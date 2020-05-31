package com.dsl.expressions.param;

import com.dsl.expressions.Expression;

public interface AfterCaseWhen {
    AfterCaseThen then(Object s);

    AfterCaseThen then(Expression e);
}
