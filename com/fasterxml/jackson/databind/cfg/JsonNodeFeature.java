package com.fasterxml.jackson.databind.cfg;

public enum JsonNodeFeature implements DatatypeFeature {
  READ_NULL_PROPERTIES(true),
  WRITE_NULL_PROPERTIES(true),
  WRITE_PROPERTIES_SORTED(false),
  STRIP_TRAILING_BIGDECIMAL_ZEROES(true);
  
  private static final int FEATURE_INDEX = 1;
  
  private final boolean _enabledByDefault;
  
  private final int _mask;
  
  JsonNodeFeature(boolean enabledByDefault) {
    this._enabledByDefault = enabledByDefault;
    this._mask = 1 << ordinal();
  }
  
  public boolean enabledByDefault() {
    return this._enabledByDefault;
  }
  
  public boolean enabledIn(int flags) {
    return ((flags & this._mask) != 0);
  }
  
  public int getMask() {
    return this._mask;
  }
  
  public int featureIndex() {
    return 1;
  }
}
