package org.java_websocket.extensions;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;

public interface IExtension {
  void decodeFrame(Framedata paramFramedata) throws InvalidDataException;
  
  void encodeFrame(Framedata paramFramedata);
  
  boolean acceptProvidedExtensionAsServer(String paramString);
  
  boolean acceptProvidedExtensionAsClient(String paramString);
  
  void isFrameValid(Framedata paramFramedata) throws InvalidDataException;
  
  String getProvidedExtensionAsClient();
  
  String getProvidedExtensionAsServer();
  
  IExtension copyInstance();
  
  void reset();
  
  String toString();
}
