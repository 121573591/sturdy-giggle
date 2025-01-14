package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum CommentTypeTag implements ProtocolMessageEnum {
  COMMENTTYPETAGUNKNOWN(0),
  COMMENTTYPETAGSTAR(1),
  UNRECOGNIZED(-1);
  
  public static final int COMMENTTYPETAGUNKNOWN_VALUE = 0;
  
  public static final int COMMENTTYPETAGSTAR_VALUE = 1;
  
  private static final Internal.EnumLiteMap<CommentTypeTag> internalValueMap;
  
  private static final CommentTypeTag[] VALUES;
  
  private final int value;
  
  public final int getNumber() {
    if (this == UNRECOGNIZED)
      throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
    return this.value;
  }
  
  public static CommentTypeTag forNumber(int value) {
    switch (value) {
      case 0:
        return COMMENTTYPETAGUNKNOWN;
      case 1:
        return COMMENTTYPETAGSTAR;
    } 
    return null;
  }
  
  public static Internal.EnumLiteMap<CommentTypeTag> internalGetValueMap() {
    return internalValueMap;
  }
  
  static {
    internalValueMap = new Internal.EnumLiteMap<CommentTypeTag>() {
        public CommentTypeTag findValueByNumber(int number) {
          return CommentTypeTag.forNumber(number);
        }
      };
    VALUES = values();
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
    return Tiktok.getDescriptor().getEnumTypes().get(0);
  }
  
  CommentTypeTag(int value) {
    this.value = value;
  }
}
