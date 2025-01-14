package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class WebUserPauseTypeOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum WebUserPauseType implements ProtocolMessageEnum {
    UNKNOWN_USER_PAUSE_TYPE(0),
    BACKGROUND(1),
    UNRECOGNIZED(-1);
    
    public static final int UNKNOWN_USER_PAUSE_TYPE_VALUE = 0;
    
    public static final int BACKGROUND_VALUE = 1;
    
    private static final Internal.EnumLiteMap<WebUserPauseType> internalValueMap = new Internal.EnumLiteMap<WebUserPauseType>() {
        public WebUserPauseTypeOuterClass.WebUserPauseType findValueByNumber(int number) {
          return WebUserPauseTypeOuterClass.WebUserPauseType.forNumber(number);
        }
      };
    
    private static final WebUserPauseType[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static WebUserPauseType forNumber(int value) {
      switch (value) {
        case 0:
          return UNKNOWN_USER_PAUSE_TYPE;
        case 1:
          return BACKGROUND;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<WebUserPauseType> internalGetValueMap() {
      return internalValueMap;
    }
    
    static {
    
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
      if (this == UNRECOGNIZED)
        throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value."); 
      return getDescriptor().getValues().get(ordinal());
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
      return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
      return WebUserPauseTypeOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    WebUserPauseType(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\026WebUserPauseType.proto*?\n\020WebUserPauseType\022\033\n\027UNKNOWN_USER_PAUSE_TYPE\020\000\022\016\n\nBACKGROUND\020\001B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
