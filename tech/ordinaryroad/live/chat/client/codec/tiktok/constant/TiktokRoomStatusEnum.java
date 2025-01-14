package tech.ordinaryroad.live.chat.client.codec.tiktok.constant;

public enum TiktokRoomStatusEnum {
  STOPPED(4),
  LIVING(2);
  
  TiktokRoomStatusEnum(int code) {
    this.code = code;
  }
  
  private final int code;
  
  public int getCode() {
    return this.code;
  }
  
  public static TiktokRoomStatusEnum getByCode(int code) {
    for (TiktokRoomStatusEnum value : values()) {
      if (value.getCode() == code)
        return value; 
    } 
    return null;
  }
}
