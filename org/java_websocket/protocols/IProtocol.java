package org.java_websocket.protocols;

public interface IProtocol {
  boolean acceptProvidedProtocol(String paramString);
  
  String getProvidedProtocol();
  
  IProtocol copyInstance();
  
  String toString();
}
