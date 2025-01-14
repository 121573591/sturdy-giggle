package tech.ordinaryroad.live.chat.client.commons.base.msg;

import tech.ordinaryroad.live.chat.client.commons.base.constant.LiveStatusAction;

public interface ILiveStatusChangeMsg extends IMsg {
  LiveStatusAction getLiveStatusAction();
}
