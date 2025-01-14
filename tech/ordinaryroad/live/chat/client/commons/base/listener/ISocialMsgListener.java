package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface ISocialMsgListener<T, SocialMsg> {
  default void onSocialMsg(T t, SocialMsg msg) {
    onSocialMsg(msg);
  }
  
  default void onSocialMsg(SocialMsg msg) {}
}
