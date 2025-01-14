package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class WebRedPackCoverTypeOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum WebRedPackCoverType implements ProtocolMessageEnum {
    UNKNOWN_COVER_TYPE(0),
    NORMAL_COVER(1),
    PRETTY_COVER(2),
    UNRECOGNIZED(-1);
    
    public static final int UNKNOWN_COVER_TYPE_VALUE = 0;
    
    public static final int NORMAL_COVER_VALUE = 1;
    
    public static final int PRETTY_COVER_VALUE = 2;
    
    private static final Internal.EnumLiteMap<WebRedPackCoverType> internalValueMap = new Internal.EnumLiteMap<WebRedPackCoverType>() {
        public WebRedPackCoverTypeOuterClass.WebRedPackCoverType findValueByNumber(int number) {
          return WebRedPackCoverTypeOuterClass.WebRedPackCoverType.forNumber(number);
        }
      };
    
    private static final WebRedPackCoverType[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static WebRedPackCoverType forNumber(int value) {
      switch (value) {
        case 0:
          return UNKNOWN_COVER_TYPE;
        case 1:
          return NORMAL_COVER;
        case 2:
          return PRETTY_COVER;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<WebRedPackCoverType> internalGetValueMap() {
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
      return WebRedPackCoverTypeOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    WebRedPackCoverType(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\031WebRedPackCoverType.proto*Q\n\023WebRedPackCoverType\022\026\n\022UNKNOWN_COVER_TYPE\020\000\022\020\n\fNORMAL_COVER\020\001\022\020\n\fPRETTY_COVER\020\002B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
