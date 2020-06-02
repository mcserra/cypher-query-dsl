package com.dsl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class StringUtils {

    private StringUtils() {
    }

    public static String getString(final Object o) {
        return o instanceof AsString ? ((AsString) o).asString() : o.toString();
    }

    public static String join(final Object[] t) {
        return String.join(", ", StringUtils.asString(t));
    }

    public static <T extends AsString> String join(final Collection<T> t) {
        return String.join(", ", StringUtils.asString(t));
    }

    public static <T extends AsString> String join(final T[] t) {
        return StringUtils.join(t, ", ");
    }

    public static <T extends AsString> String join(final T[] t, final String separator) {
        return String.join(separator, StringUtils.asString(t));
    }

    public static <T extends AsString> List<String> asString(final Collection<T> t) {
        List<String> c = new ArrayList<>();
        t.forEach(t1 -> c.add(t1.asString()));
        return c;
    }

    public static String[] asString(final Object[] t) {
        String[] c = new String[t.length];
        for (int i = 0; i < t.length; i++) {
            c[i] = t[i].toString();
        }
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
}
