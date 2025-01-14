package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class ClientIdOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum ClientId implements ProtocolMessageEnum {
    NONE(0),
    IPHONE(1),
    ANDROID(2),
    WEB(3),
    PC(6),
    IPHONE_LIVE_MATE(8),
    ANDROID_LIVE_MATE(9),
    UNRECOGNIZED(-1);
    
    public static final int NONE_VALUE = 0;
    
    public static final int IPHONE_VALUE = 1;
    
    public static final int ANDROID_VALUE = 2;
    
    public static final int WEB_VALUE = 3;
    
    public static final int PC_VALUE = 6;
    
    public static final int IPHONE_LIVE_MATE_VALUE = 8;
    
    public static final int ANDROID_LIVE_MATE_VALUE = 9;
    
    private static final Internal.EnumLiteMap<ClientId> internalValueMap = new Internal.EnumLiteMap<ClientId>() {
        public ClientIdOuterClass.ClientId findValueByNumber(int number) {
          return ClientIdOuterClass.ClientId.forNumber(number);
        }
      };
    
    private static final ClientId[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static ClientId forNumber(int value) {
      switch (value) {
        case 0:
          return NONE;
        case 1:
          return IPHONE;
        case 2:
          return ANDROID;
        case 3:
          return WEB;
        case 6:
          return PC;
        case 8:
          return IPHONE_LIVE_MATE;
        case 9:
          return ANDROID_LIVE_MATE;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<ClientId> internalGetValueMap() {
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
      return ClientIdOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    ClientId(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\016ClientId.proto*k\n\bClientId\022\b\n\004NONE\020\000\022\n\n\006IPHONE\020\001\022\013\n\007ANDROID\020\002\022\007\n\003WEB\020\003\022\006\n\002PC\020\006\022\024\n\020IPHONE_LIVE_MATE\020\b\022\025\n\021ANDROID_LIVE_MATE\020\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
