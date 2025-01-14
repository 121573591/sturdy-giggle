package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

public abstract class AccessorNamingStrategy {
  public abstract String findNameForIsGetter(AnnotatedMethod paramAnnotatedMethod, String paramString);
  
  public abstract String findNameForRegularGetter(AnnotatedMethod paramAnnotatedMethod, String paramString);
  
  public abstract String findNameForMutator(AnnotatedMethod paramAnnotatedMethod, String paramString);
  
  public abstract String modifyFieldName(AnnotatedField paramAnnotatedField, String paramString);
  
  public static class Base extends AccessorNamingStrategy implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String findNameForIsGetter(AnnotatedMethod method, String name) {
      return null;
    }
    
    public String findNameForRegularGetter(AnnotatedMethod method, String name) {
      return null;
    }
    
    public String findNameForMutator(AnnotatedMethod method, String name) {
      return null;
    }
    
    public String modifyFieldName(AnnotatedField field, String name) {
      return name;
    }
  }
  
  public static abstract class Provider implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public abstract AccessorNamingStrategy forPOJO(MapperConfig<?> param1MapperConfig, AnnotatedClass param1AnnotatedClass);
    
    public abstract AccessorNamingStrategy forBuilder(MapperConfig<?> param1MapperConfig, AnnotatedClass param1AnnotatedClass, BeanDescription param1BeanDescription);
    
    public abstract AccessorNamingStrategy forRecord(MapperConfig<?> param1MapperConfig, AnnotatedClass param1AnnotatedClass);
  }
}
