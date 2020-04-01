package com.dsl.expressions;

import com.dsl.AsString;

/**
 * The types of expression should be:
 * - Logical Expression - returns a boolean
 * - Selector Expression - returns an object, can be aliased
 * - Path Expression - specific expression to build paths
 * - Final Expression - Expression that is final, nothing to be done upon it
 * - Identifier Expression - specific selector expression for neo entities
 */
public interface Expression extends AsString {
}
