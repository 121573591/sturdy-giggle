package tech.ordinaryroad.live.chat.client.codec.douyin.constant;

import cn.hutool.core.util.StrUtil;

public enum DouyinCmdEnum {
  WebcastChatMessage, WebcastGiftMessage, WebcastLikeMessage, WebcastMemberMessage, WebcastRoomStatsMessage, WebcastSocialMessage, WebcastRoomUserSeqMessage, WebcastFansclubMessage, WebcastControlMessage;
  
  public static DouyinCmdEnum getByName(String name) {
    if (StrUtil.isBlank(name))
      return null; 
    for (DouyinCmdEnum value : values()) {
      if (value.name().equals(name))
        return value; 
    } 
    return null;
  }
}
