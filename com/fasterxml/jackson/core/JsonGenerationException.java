package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.exc.StreamWriteException;

public class JsonGenerationException extends StreamWriteException {
  private static final long serialVersionUID = 123L;
  
  @Deprecated
  public JsonGenerationException(Throwable rootCause) {
    super(rootCause, null);
  }
  
  @Deprecated
  public JsonGenerationException(String msg) {
    super(msg, null);
  }
  
  @Deprecated
  public JsonGenerationException(String msg, Throwable rootCause) {
    super(msg, rootCause, null);
  }
  
  public JsonGenerationException(Throwable rootCause, JsonGenerator g) {
    super(rootCause, g);
  }
  
  public JsonGenerationException(String msg, JsonGenerator g) {
    super(msg, g);
    this._processor = g;
  }
  
  public JsonGenerationException(String msg, Throwable rootCause, JsonGenerator g) {
    super(msg, rootCause, g);
    this._processor = g;
  }
  
  public JsonGenerationException withGenerator(JsonGenerator g) {
    this._processor = g;
    return this;
  }
  
  public JsonGenerator getProcessor() {
    return this._processor;
  }
}
