package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class WebLiveAssistantTypeOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum WebLiveAssistantType implements ProtocolMessageEnum {
    UNKNOWN_ASSISTANT_TYPE(0),
    SUPER(1),
    JUNIOR(2),
    UNRECOGNIZED(-1);
    
    public static final int UNKNOWN_ASSISTANT_TYPE_VALUE = 0;
    
    public static final int SUPER_VALUE = 1;
    
    public static final int JUNIOR_VALUE = 2;
    
    private static final Internal.EnumLiteMap<WebLiveAssistantType> internalValueMap = new Internal.EnumLiteMap<WebLiveAssistantType>() {
        public WebLiveAssistantTypeOuterClass.WebLiveAssistantType findValueByNumber(int number) {
          return WebLiveAssistantTypeOuterClass.WebLiveAssistantType.forNumber(number);
        }
      };
    
    private static final WebLiveAssistantType[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static WebLiveAssistantType forNumber(int value) {
      switch (value) {
        case 0:
          return UNKNOWN_ASSISTANT_TYPE;
        case 1:
          return SUPER;
        case 2:
          return JUNIOR;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<WebLiveAssistantType> internalGetValueMap() {
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
      return WebLiveAssistantTypeOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    WebLiveAssistantType(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\032WebLiveAssistantType.proto*I\n\024WebLiveAssistantType\022\032\n\026UNKNOWN_ASSISTANT_TYPE\020\000\022\t\n\005SUPER\020\001\022\n\n\006JUNIOR\020\002B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
