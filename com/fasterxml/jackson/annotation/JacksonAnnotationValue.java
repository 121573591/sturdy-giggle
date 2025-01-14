package com.fasterxml.jackson.annotation;

public interface JacksonAnnotationValue<A extends java.lang.annotation.Annotation> {
  Class<A> valueFor();
}
