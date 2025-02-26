package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;

public class MismatchedInputException extends JsonMappingException {
  protected Class<?> _targetType;
  
  protected MismatchedInputException(JsonParser p, String msg) {
    this(p, msg, (JavaType)null);
  }
  
  protected MismatchedInputException(JsonParser p, String msg, JsonLocation loc) {
    super((Closeable)p, msg, loc);
  }
  
  protected MismatchedInputException(JsonParser p, String msg, Class<?> targetType) {
    super((Closeable)p, msg);
    this._targetType = targetType;
  }
  
  protected MismatchedInputException(JsonParser p, String msg, JavaType targetType) {
    super((Closeable)p, msg);
    this._targetType = ClassUtil.rawClass(targetType);
  }
  
  @Deprecated
  public static MismatchedInputException from(JsonParser p, String msg) {
    return from(p, (Class)null, msg);
  }
  
  public static MismatchedInputException from(JsonParser p, JavaType targetType, String msg) {
    return new MismatchedInputException(p, msg, targetType);
  }
  
  public static MismatchedInputException from(JsonParser p, Class<?> targetType, String msg) {
    return new MismatchedInputException(p, msg, targetType);
  }
  
  public MismatchedInputException setTargetType(JavaType t) {
    this._targetType = t.getRawClass();
    return this;
  }
  
  public Class<?> getTargetType() {
    return this._targetType;
  }
}
