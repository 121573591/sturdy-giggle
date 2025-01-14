package com.google.protobuf;

public final class LegacyDescriptorsUtil {
  public static final class LegacyFileDescriptor {
    public enum Syntax {
      UNKNOWN("unknown"),
      PROTO2("proto2"),
      PROTO3("proto3");
      
      final String name;
      
      Syntax(String name) {
        this.name = name;
      }
    }
    
    public static Syntax getSyntax(Descriptors.FileDescriptor descriptor) {
      switch (descriptor.getSyntax()) {
        case UNKNOWN:
          return Syntax.UNKNOWN;
        case PROTO2:
          return Syntax.PROTO2;
        case PROTO3:
          return Syntax.PROTO3;
      } 
      throw new IllegalArgumentException("Unexpected syntax");
    }
  }
  
  public static final class LegacyFieldDescriptor {
    public static boolean hasOptionalKeyword(Descriptors.FieldDescriptor descriptor) {
      return descriptor.hasOptionalKeyword();
    }
  }
  
  public static final class LegacyOneofDescriptor {
    public static boolean isSynthetic(Descriptors.OneofDescriptor descriptor) {
      return descriptor.isSynthetic();
    }
  }
}
