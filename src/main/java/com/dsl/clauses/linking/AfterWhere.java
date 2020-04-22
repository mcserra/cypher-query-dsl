package com.dsl.clauses.linking;

import com.dsl.AsString;
import com.dsl.expressions.logical.LogicalExpression;

public interface AfterWhere extends AsString {
    AfterWhere and(String expression);

    AfterWhere and(LogicalExpression logicalExpression);

    AfterWhere or(String expression);

    AfterWhere or(LogicalExpression logicalExpression);

    AfterWhere xor(String expression);

    AfterWhere xor(LogicalExpression logicalExpression);
}
