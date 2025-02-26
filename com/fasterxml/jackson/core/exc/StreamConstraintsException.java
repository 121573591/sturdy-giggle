package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

public class StreamConstraintsException extends JsonProcessingException {
  private static final long serialVersionUID = 2L;
  
  public StreamConstraintsException(String msg) {
    super(msg);
  }
  
  public StreamConstraintsException(String msg, JsonLocation loc) {
    super(msg, loc);
  }
}
