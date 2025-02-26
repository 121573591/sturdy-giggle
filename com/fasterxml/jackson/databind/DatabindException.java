package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class DatabindException extends JsonProcessingException {
  private static final long serialVersionUID = 3L;
  
  protected DatabindException(String msg, JsonLocation loc, Throwable rootCause) {
    super(msg, loc, rootCause);
  }
  
  protected DatabindException(String msg) {
    super(msg);
  }
  
  protected DatabindException(String msg, JsonLocation loc) {
    this(msg, loc, null);
  }
  
  protected DatabindException(String msg, Throwable rootCause) {
    this(msg, null, rootCause);
  }
  
  public abstract void prependPath(Object paramObject, String paramString);
  
  public abstract void prependPath(Object paramObject, int paramInt);
}
