package com.dsl.variables;

import com.dsl.AsString;

public class Variable<T extends AsString> implements AsString {

  private final String name;
  private final T value;

  public Variable(String name, T value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public T getValue() {
    return this.value;
  }

  @Override
  public String asString() {
    return String.format("%s=%s", name, value.asString());
  }
}
