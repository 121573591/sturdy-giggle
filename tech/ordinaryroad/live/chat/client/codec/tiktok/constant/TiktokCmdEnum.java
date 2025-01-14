package tech.ordinaryroad.live.chat.client.codec.tiktok.constant;

import cn.hutool.core.util.StrUtil;

public enum TiktokCmdEnum {
  WebcastChatMessage, WebcastGiftMessage, WebcastLikeMessage, WebcastMemberMessage, WebcastRoomStatsMessage, WebcastSocialMessage, WebcastRoomUserSeqMessage, WebcastFansclubMessage, WebcastControlMessage;
  
  public static TiktokCmdEnum getByName(String name) {
    if (StrUtil.isBlank(name))
      return null; 
    for (TiktokCmdEnum value : values()) {
      if (value.name().equals(name))
        return value; 
    } 
    return null;
  }
}
