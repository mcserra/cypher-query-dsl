package com.dsl.expressions.path;

import com.dsl.AsString;
import com.dsl.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Properties on vertices or edges.
 */
public class GraphProperties implements AsString {
    private Map<String, Object> properties;

    public GraphProperties() {
        properties = new HashMap<>();
    }

    public void add(final Object... o) {
        if (o.length % 2 == 0) {
            for (int i = 0; i < o.length; i += 2) {
                properties.put((String) o[i], o[i + 1]);
            }
        }
    }

    @Override
    public String asString() {
        if (properties.isEmpty()) {
            return "";
        }
        return String.format(" {%s}", String.join(", ", StringUtils.asString(properties, ": ")));
    }
}
