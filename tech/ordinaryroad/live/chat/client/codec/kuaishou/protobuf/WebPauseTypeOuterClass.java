package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class WebPauseTypeOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum WebPauseType implements ProtocolMessageEnum {
    UNKNOWN_PAUSE_TYPE(0),
    TELEPHONE(1),
    SHARE(2),
    UNRECOGNIZED(-1);
    
    public static final int UNKNOWN_PAUSE_TYPE_VALUE = 0;
    
    public static final int TELEPHONE_VALUE = 1;
    
    public static final int SHARE_VALUE = 2;
    
    private static final Internal.EnumLiteMap<WebPauseType> internalValueMap = new Internal.EnumLiteMap<WebPauseType>() {
        public WebPauseTypeOuterClass.WebPauseType findValueByNumber(int number) {
          return WebPauseTypeOuterClass.WebPauseType.forNumber(number);
        }
      };
    
    private static final WebPauseType[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static WebPauseType forNumber(int value) {
      switch (value) {
        case 0:
          return UNKNOWN_PAUSE_TYPE;
        case 1:
          return TELEPHONE;
        case 2:
          return SHARE;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<WebPauseType> internalGetValueMap() {
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
      return WebPauseTypeOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    WebPauseType(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\022WebPauseType.proto*@\n\fWebPauseType\022\026\n\022UNKNOWN_PAUSE_TYPE\020\000\022\r\n\tTELEPHONE\020\001\022\t\n\005SHARE\020\002B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
