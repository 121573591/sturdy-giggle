package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.Serializable;

public abstract class NopAnnotationIntrospector extends AnnotationIntrospector implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final NopAnnotationIntrospector instance = new NopAnnotationIntrospector() {
      private static final long serialVersionUID = 1L;
      
      public Version version() {
        return PackageVersion.VERSION;
      }
    };
  
  public Version version() {
    return Version.unknownVersion();
  }
}
