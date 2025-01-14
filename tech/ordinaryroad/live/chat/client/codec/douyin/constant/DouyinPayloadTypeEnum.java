package tech.ordinaryroad.live.chat.client.codec.douyin.constant;

import cn.hutool.core.util.StrUtil;

public enum DouyinPayloadTypeEnum {
  HB("hb"),
  MSG("msg"),
  ACK("ack");
  
  DouyinPayloadTypeEnum(String code) {
    this.code = code;
  }
  
  private final String code;
  
  public String getCode() {
    return this.code;
  }
  
  public static DouyinPayloadTypeEnum getByCode(String code) {
    if (StrUtil.isBlank(code))
      return null; 
    for (DouyinPayloadTypeEnum value : values()) {
      if (value.getCode().equals(code))
        return value; 
    } 
    return null;
  }
}
