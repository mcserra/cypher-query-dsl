package com.dsl.clauses.linking;

import com.dsl.AsString;

public interface WithAlias extends AsString, AfterWith {
    AfterWith as(String selector);
}
