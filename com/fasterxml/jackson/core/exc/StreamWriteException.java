package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class StreamWriteException extends JsonProcessingException {
  private static final long serialVersionUID = 2L;
  
  protected transient JsonGenerator _processor;
  
  protected StreamWriteException(Throwable rootCause, JsonGenerator g) {
    super(rootCause);
    this._processor = g;
  }
  
  protected StreamWriteException(String msg, JsonGenerator g) {
    super(msg, (JsonLocation)null);
    this._processor = g;
  }
  
  protected StreamWriteException(String msg, Throwable rootCause, JsonGenerator g) {
    super(msg, null, rootCause);
    this._processor = g;
  }
  
  public JsonGenerator getProcessor() {
    return this._processor;
  }
  
  public abstract StreamWriteException withGenerator(JsonGenerator paramJsonGenerator);
}
