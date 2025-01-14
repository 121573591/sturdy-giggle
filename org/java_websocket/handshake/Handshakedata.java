package org.java_websocket.handshake;

import java.util.Iterator;

public interface Handshakedata {
  Iterator<String> iterateHttpFields();
  
  String getFieldValue(String paramString);
  
  boolean hasFieldValue(String paramString);
  
  byte[] getContent();
}
