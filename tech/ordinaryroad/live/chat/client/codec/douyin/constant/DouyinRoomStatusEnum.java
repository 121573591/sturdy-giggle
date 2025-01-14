package tech.ordinaryroad.live.chat.client.codec.douyin.constant;

public enum DouyinRoomStatusEnum {
  STOPPED(4),
  LIVING(2);
  
  DouyinRoomStatusEnum(int code) {
    this.code = code;
  }
  
  private final int code;
  
  public int getCode() {
    return this.code;
  }
  
  public static DouyinRoomStatusEnum getByCode(int code) {
    for (DouyinRoomStatusEnum value : values()) {
      if (value.getCode() == code)
        return value; 
    } 
    return null;
  }
}
