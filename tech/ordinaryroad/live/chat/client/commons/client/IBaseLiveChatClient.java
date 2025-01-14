package tech.ordinaryroad.live.chat.client.commons.client;

import java.util.List;
import java.util.function.Consumer;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;
import tech.ordinaryroad.live.chat.client.commons.client.listener.IClientStatusChangeListener;

public interface IBaseLiveChatClient<MsgListener extends tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener<?, ?>> {
  void init();
  
  boolean addMsgListener(MsgListener paramMsgListener);
  
  boolean addMsgListeners(List<MsgListener> paramList);
  
  boolean removeMsgListener(MsgListener paramMsgListener);
  
  boolean removeMsgListeners(List<MsgListener> paramList);
  
  void removeAllMsgListeners();
  
  void connect(Runnable paramRunnable, Consumer<Throwable> paramConsumer);
  
  void connect(Runnable paramRunnable);
  
  void connect();
  
  void disconnect(boolean paramBoolean);
  
  void disconnect();
  
  void destroy();
  
  void send(Object paramObject);
  
  void send(Object paramObject, Runnable paramRunnable, Consumer<Throwable> paramConsumer);
  
  void send(Object paramObject, Runnable paramRunnable);
  
  void send(Object paramObject, Consumer<Throwable> paramConsumer);
  
  void sendDanmu(Object paramObject);
  
  void sendDanmu(Object paramObject, Runnable paramRunnable, Consumer<Throwable> paramConsumer);
  
  void sendDanmu(Object paramObject, Runnable paramRunnable);
  
  void sendDanmu(Object paramObject, Consumer<Throwable> paramConsumer);
  
  void clickLike(int paramInt);
  
  void clickLike(int paramInt, Runnable paramRunnable, Consumer<Throwable> paramConsumer);
  
  void clickLike(int paramInt, Runnable paramRunnable);
  
  void clickLike(int paramInt, Consumer<Throwable> paramConsumer);
  
  ClientStatusEnums getStatus();
  
  void addStatusChangeListener(IClientStatusChangeListener paramIClientStatusChangeListener);
  
  void removeStatusChangeListener(IClientStatusChangeListener paramIClientStatusChangeListener);
}
