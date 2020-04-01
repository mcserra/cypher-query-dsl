package com.dsl.expressions.path;

import com.dsl.AsString;

/**
 * Represents a part of a PathExpression.
 */
public interface PathProperty extends AsString {
    void addProperties(Object... props);
}
