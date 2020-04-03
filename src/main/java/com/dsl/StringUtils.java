package com.dsl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class StringUtils {

    public static <T extends AsString> List<String> asString(final Collection<T> t) {
        List<String> c = new ArrayList<>();
        t.forEach(t1 -> c.add(t1.asString()));
        return c;
    }

    public static <T extends AsString> String[] asString(final T[] t) {
        String[] c = new String[t.length];
        for (int i = 0; i < t.length; i++) {
            c[i] = t[i].asString();
        }
        return c;
    }

    public static List<String> asString(final Map<String, Object> t, final String separator) {
        List<String> strings = new ArrayList<>();
        t.forEach((s, o) -> {
            String oString = o.toString();
            if (o instanceof AsString) {
                oString = ((AsString) o).asString();
            }
            strings.add(String.format("%s%s%s", s, separator, oString));
        });
        return strings;
    }

    public static <T extends AsString> List<String> string(final Map<String, T> t, final String separator) {
        List<String> strings = new ArrayList<>();
        t.forEach((s, o) -> strings.add(String.format("%s%s%s", s, separator, o.asString())));
        return strings;
    }
}
