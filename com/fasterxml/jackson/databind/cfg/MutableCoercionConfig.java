package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;

public class MutableCoercionConfig extends CoercionConfig implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public MutableCoercionConfig() {}
  
  protected MutableCoercionConfig(MutableCoercionConfig src) {
    super(src);
  }
  
  public MutableCoercionConfig copy() {
    return new MutableCoercionConfig(this);
  }
  
  public MutableCoercionConfig setCoercion(CoercionInputShape shape, CoercionAction action) {
    this._coercionsByShape[shape.ordinal()] = action;
    return this;
  }
  
  public MutableCoercionConfig setAcceptBlankAsEmpty(Boolean state) {
    this._acceptBlankAsEmpty = state;
    return this;
  }
}
