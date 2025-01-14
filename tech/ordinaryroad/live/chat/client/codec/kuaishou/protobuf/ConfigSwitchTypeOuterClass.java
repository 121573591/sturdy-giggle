package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class ConfigSwitchTypeOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum ConfigSwitchType implements ProtocolMessageEnum {
    UNKNOWN(0),
    HIDE_BARRAGE(1),
    HIDE_SPECIAL_EFFECT(2),
    UNRECOGNIZED(-1);
    
    public static final int UNKNOWN_VALUE = 0;
    
    public static final int HIDE_BARRAGE_VALUE = 1;
    
    public static final int HIDE_SPECIAL_EFFECT_VALUE = 2;
    
    private static final Internal.EnumLiteMap<ConfigSwitchType> internalValueMap = new Internal.EnumLiteMap<ConfigSwitchType>() {
        public ConfigSwitchTypeOuterClass.ConfigSwitchType findValueByNumber(int number) {
          return ConfigSwitchTypeOuterClass.ConfigSwitchType.forNumber(number);
        }
      };
    
    private static final ConfigSwitchType[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static ConfigSwitchType forNumber(int value) {
      switch (value) {
        case 0:
          return UNKNOWN;
        case 1:
          return HIDE_BARRAGE;
        case 2:
          return HIDE_SPECIAL_EFFECT;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<ConfigSwitchType> internalGetValueMap() {
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
      return ConfigSwitchTypeOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    ConfigSwitchType(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\026ConfigSwitchType.proto*J\n\020ConfigSwitchType\022\013\n\007UNKNOWN\020\000\022\020\n\fHIDE_BARRAGE\020\001\022\027\n\023HIDE_SPECIAL_EFFECT\020\002B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
