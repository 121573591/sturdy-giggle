package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum RoomMsgTypeEnum implements ProtocolMessageEnum {
  DEFAULTROOMMSG(0),
  ECOMLIVEREPLAYSAVEROOMMSG(1),
  CONSUMERRELATIONROOMMSG(2),
  JUMANJIDATAAUTHNOTIFYMSG(3),
  VSWELCOMEMSG(4),
  MINORREFUNDMSG(5),
  PAIDLIVEROOMNOTIFYANCHORMSG(6),
  HOSTTEAMSYSTEMMSG(7),
  UNRECOGNIZED(-1);
  
  public static final int DEFAULTROOMMSG_VALUE = 0;
  
  public static final int ECOMLIVEREPLAYSAVEROOMMSG_VALUE = 1;
  
  public static final int CONSUMERRELATIONROOMMSG_VALUE = 2;
  
  public static final int JUMANJIDATAAUTHNOTIFYMSG_VALUE = 3;
  
  public static final int VSWELCOMEMSG_VALUE = 4;
  
  public static final int MINORREFUNDMSG_VALUE = 5;
  
  public static final int PAIDLIVEROOMNOTIFYANCHORMSG_VALUE = 6;
  
  public static final int HOSTTEAMSYSTEMMSG_VALUE = 7;
  
  private static final Internal.EnumLiteMap<RoomMsgTypeEnum> internalValueMap;
  
  private static final RoomMsgTypeEnum[] VALUES;
  
  private final int value;
  
  public final int getNumber() {
    if (this == UNRECOGNIZED)
      throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
    return this.value;
  }
  
  public static RoomMsgTypeEnum forNumber(int value) {
    switch (value) {
      case 0:
        return DEFAULTROOMMSG;
      case 1:
        return ECOMLIVEREPLAYSAVEROOMMSG;
      case 2:
        return CONSUMERRELATIONROOMMSG;
      case 3:
        return JUMANJIDATAAUTHNOTIFYMSG;
      case 4:
        return VSWELCOMEMSG;
      case 5:
        return MINORREFUNDMSG;
      case 6:
        return PAIDLIVEROOMNOTIFYANCHORMSG;
      case 7:
        return HOSTTEAMSYSTEMMSG;
    } 
    return null;
  }
  
  public static Internal.EnumLiteMap<RoomMsgTypeEnum> internalGetValueMap() {
    return internalValueMap;
  }
  
  static {
    internalValueMap = new Internal.EnumLiteMap<RoomMsgTypeEnum>() {
        public RoomMsgTypeEnum findValueByNumber(int number) {
          return RoomMsgTypeEnum.forNumber(number);
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
    return Douyin.getDescriptor().getEnumTypes().get(1);
  }
  
  RoomMsgTypeEnum(int value) {
    this.value = value;
  }
}
