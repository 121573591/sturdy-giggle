package tech.ordinaryroad.live.chat.client.codec.kuaishou.constant;

public enum RoomInfoGetTypeEnum {
  COOKIE(1),
  NOT_COOKIE(2);
  
  RoomInfoGetTypeEnum(int code) {
    this.code = code;
  }
  
  private final int code;
  
  public int getCode() {
    return this.code;
  }
  
  public static RoomInfoGetTypeEnum getByCode(int code) {
    for (RoomInfoGetTypeEnum value : values()) {
      if (value.code == code)
        return value; 
    } 
    return null;
  }
}
