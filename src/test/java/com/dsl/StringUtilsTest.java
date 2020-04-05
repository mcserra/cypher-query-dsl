package com.dsl;

import com.dsl.expressions.param.Date;
import com.dsl.expressions.param.Literal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void testAsStringWithCollection() {
        List<String> s = StringUtils.asString(List.of(new Literal("a"), new Date("2019-01-01")));
        Assertions.assertEquals(List.of("'a'", "date('2019-01-01')"), s);
    }

    @Test
    void testAsStringWithArray() {
        String[] s = StringUtils.asString(new AsString[]{new Literal("a"), new Date("2019-01-01")});
        Assertions.assertEquals(List.of("'a'", "date('2019-01-01')"), Arrays.asList(s));
    }

    @Test
    void testAsStringWithObjects() {
        var map = Map.of("foo", "example", "bar", new Literal("a"));
        Assertions.assertTrue(List.of("foo::example", "bar::'a'").containsAll(StringUtils.asString(map, "::")));
    }
}
