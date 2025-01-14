package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface ILikeMsgListener<T, LikeMsg> {
  default void onLikeMsg(T t, LikeMsg msg) {
    onLikeMsg(msg);
  }
  
  default void onLikeMsg(LikeMsg msg) {}
}
