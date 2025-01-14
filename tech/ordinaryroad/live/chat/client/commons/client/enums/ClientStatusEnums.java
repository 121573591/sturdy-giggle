package tech.ordinaryroad.live.chat.client.commons.client.enums;

public enum ClientStatusEnums {
  NEW(0),
  INITIALIZED(1),
  CONNECTING(100),
  RECONNECTING(101),
  CONNECTED(200),
  CONNECT_FAILED(401),
  DISCONNECTED(400),
  DESTROYED(-1);
  
  private final int code;
  
  public int getCode() {
    return this.code;
  }
  
  ClientStatusEnums(int order) {
    this.code = order;
  }
}
