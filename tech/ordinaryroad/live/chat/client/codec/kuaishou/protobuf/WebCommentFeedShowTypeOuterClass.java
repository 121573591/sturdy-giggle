package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class WebCommentFeedShowTypeOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum WebCommentFeedShowType implements ProtocolMessageEnum {
    FEED_SHOW_UNKNOWN(0),
    FEED_SHOW_NORMAL(1),
    FEED_HIDDEN(2),
    UNRECOGNIZED(-1);
    
    public static final int FEED_SHOW_UNKNOWN_VALUE = 0;
    
    public static final int FEED_SHOW_NORMAL_VALUE = 1;
    
    public static final int FEED_HIDDEN_VALUE = 2;
    
    private static final Internal.EnumLiteMap<WebCommentFeedShowType> internalValueMap = new Internal.EnumLiteMap<WebCommentFeedShowType>() {
        public WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType findValueByNumber(int number) {
          return WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType.forNumber(number);
        }
      };
    
    private static final WebCommentFeedShowType[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static WebCommentFeedShowType forNumber(int value) {
      switch (value) {
        case 0:
          return FEED_SHOW_UNKNOWN;
        case 1:
          return FEED_SHOW_NORMAL;
        case 2:
          return FEED_HIDDEN;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<WebCommentFeedShowType> internalGetValueMap() {
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
      return WebCommentFeedShowTypeOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    WebCommentFeedShowType(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\034WebCommentFeedShowType.proto*V\n\026WebCommentFeedShowType\022\025\n\021FEED_SHOW_UNKNOWN\020\000\022\024\n\020FEED_SHOW_NORMAL\020\001\022\017\n\013FEED_HIDDEN\020\002B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
