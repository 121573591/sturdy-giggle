package com.fasterxml.jackson.databind;

public class RuntimeJsonMappingException extends RuntimeException {
  public RuntimeJsonMappingException(JsonMappingException cause) {
    super((Throwable)cause);
  }
  
  public RuntimeJsonMappingException(String message) {
    super(message);
  }
  
  public RuntimeJsonMappingException(String message, JsonMappingException cause) {
    super(message, (Throwable)cause);
  }
}
