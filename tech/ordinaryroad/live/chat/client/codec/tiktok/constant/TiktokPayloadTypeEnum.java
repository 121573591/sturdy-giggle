package tech.ordinaryroad.live.chat.client.codec.tiktok.constant;

import cn.hutool.core.util.StrUtil;

public enum TiktokPayloadTypeEnum {
  HB("hb"),
  MSG("msg"),
  ACK("ack");
  
  TiktokPayloadTypeEnum(String code) {
    this.code = code;
  }
  
  private final String code;
  
  public String getCode() {
    return this.code;
  }
  
  public static TiktokPayloadTypeEnum getByCode(String code) {
    if (StrUtil.isBlank(code))
      return null; 
    for (TiktokPayloadTypeEnum value : values()) {
      if (value.getCode().equals(code))
        return value; 
    } 
    return null;
  }
}
