package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonParser;

public enum JsonReadFeature implements FormatFeature {
  ALLOW_JAVA_COMMENTS(false, JsonParser.Feature.ALLOW_COMMENTS),
  ALLOW_YAML_COMMENTS(false, JsonParser.Feature.ALLOW_YAML_COMMENTS),
  ALLOW_SINGLE_QUOTES(false, JsonParser.Feature.ALLOW_SINGLE_QUOTES),
  ALLOW_UNQUOTED_FIELD_NAMES(false, JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES),
  ALLOW_UNESCAPED_CONTROL_CHARS(false, JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS),
  ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false, JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER),
  ALLOW_LEADING_ZEROS_FOR_NUMBERS(false, JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS),
  ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS(false, JsonParser.Feature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS),
  ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS(false, JsonParser.Feature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS),
  ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS(false, JsonParser.Feature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS),
  ALLOW_NON_NUMERIC_NUMBERS(false, JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS),
  ALLOW_MISSING_VALUES(false, JsonParser.Feature.ALLOW_MISSING_VALUES),
  ALLOW_TRAILING_COMMA(false, JsonParser.Feature.ALLOW_TRAILING_COMMA);
  
  private final boolean _defaultState;
  
  private final int _mask;
  
  private final JsonParser.Feature _mappedFeature;
  
  public static int collectDefaults() {
    int flags = 0;
    for (JsonReadFeature f : values()) {
      if (f.enabledByDefault())
        flags |= f.getMask(); 
    } 
    return flags;
  }
  
  JsonReadFeature(boolean defaultState, JsonParser.Feature mapTo) {
    this._defaultState = defaultState;
    this._mask = 1 << ordinal();
    this._mappedFeature = mapTo;
  }
  
  public boolean enabledByDefault() {
    return this._defaultState;
  }
  
  public int getMask() {
    return this._mask;
  }
  
  public boolean enabledIn(int flags) {
    return ((flags & this._mask) != 0);
  }
  
  public JsonParser.Feature mappedFeature() {
    return this._mappedFeature;
  }
}
