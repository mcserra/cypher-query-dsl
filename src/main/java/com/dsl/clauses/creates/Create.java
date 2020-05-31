package com.dsl.clauses.creates;

import com.dsl.AsString;
import com.dsl.expressions.path.PathExpression;

public interface Create extends AsString {
    AfterCreate create(PathExpression... e);

    CreatePath create();
}
