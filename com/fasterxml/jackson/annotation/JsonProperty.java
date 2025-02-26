package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonProperty {
  public static final String USE_DEFAULT_NAME = "";
  
  public static final int INDEX_UNKNOWN = -1;
  
  String value() default "";
  
  String namespace() default "";
  
  boolean required() default false;
  
  int index() default -1;
  
  String defaultValue() default "";
  
  Access access() default Access.AUTO;
  
  public enum Access {
    AUTO, READ_ONLY, WRITE_ONLY, READ_WRITE;
  }
}
