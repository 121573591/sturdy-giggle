package com.fasterxml.jackson.core;

public class JsonProcessingException extends JacksonException {
  private static final long serialVersionUID = 123L;
  
  protected JsonLocation _location;
  
  protected JsonProcessingException(String msg, JsonLocation loc, Throwable rootCause) {
    super(msg, rootCause);
    this._location = loc;
  }
  
  protected JsonProcessingException(String msg) {
    super(msg);
  }
  
  protected JsonProcessingException(String msg, JsonLocation loc) {
    this(msg, loc, null);
  }
  
  protected JsonProcessingException(String msg, Throwable rootCause) {
    this(msg, null, rootCause);
  }
  
  protected JsonProcessingException(Throwable rootCause) {
    this(null, null, rootCause);
  }
  
  public JsonLocation getLocation() {
    return this._location;
  }
  
  public void clearLocation() {
    this._location = null;
  }
  
  public String getOriginalMessage() {
    return super.getMessage();
  }
  
  public Object getProcessor() {
    return null;
  }
  
  protected String getMessageSuffix() {
    return null;
  }
  
  public String getMessage() {
    String msg = super.getMessage();
    if (msg == null)
      msg = "N/A"; 
    JsonLocation loc = getLocation();
    String suffix = getMessageSuffix();
    if (loc != null || suffix != null) {
      StringBuilder sb = new StringBuilder(100);
      sb.append(msg);
      if (suffix != null)
        sb.append(suffix); 
      if (loc != null) {
        sb.append('\n');
        sb.append(" at ");
        sb.append(loc.toString());
      } 
      msg = sb.toString();
    } 
    return msg;
  }
  
  public String toString() {
    return getClass().getName() + ": " + getMessage();
  }
}
