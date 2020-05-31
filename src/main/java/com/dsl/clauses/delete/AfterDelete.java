package com.dsl.clauses.delete;

import com.dsl.AsString;

public interface AfterDelete extends AsString {
    AsString detach();
}
