package com.dsl.expressions.param;

import com.dsl.StringUtils;
import com.dsl.expressions.AliasAbstractExpression;
import com.dsl.expressions.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a json selector expression.
 */
public class Json extends AliasAbstractExpression implements SelectorExpression {
    private Map<String, Object> map = new HashMap<>();

    public Json(Object... o) {
        fillMap(o);
    }

    private void fillMap(Object... o) {
        if (o.length % 2 == 0) {
            for (int i = 0; i < o.length; i += 2) {
                if (o[i + 1] instanceof Expression)
                    map.put((String) o[i], ((Expression)o[i + 1]).asString());
                else
                    map.put((String) o[i], o[i + 1]);

            }
        }
    }

    @Override
    public String asString() {
        return String.format("{%s}", String.join(", ", StringUtils.asString(map, ": ")));
    }
}
