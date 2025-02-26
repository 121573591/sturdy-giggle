package org.java_websocket.util;

import java.nio.ByteBuffer;

public class ByteBufferUtils {
  public static int transferByteBuffer(ByteBuffer source, ByteBuffer dest) {
    if (source == null || dest == null)
      throw new IllegalArgumentException(); 
    int fremain = source.remaining();
    int toremain = dest.remaining();
    if (fremain > toremain) {
      int limit = Math.min(fremain, toremain);
      source.limit(limit);
      dest.put(source);
      return limit;
    } 
    dest.put(source);
    return fremain;
  }
  
  public static ByteBuffer getEmptyByteBuffer() {
    return ByteBuffer.allocate(0);
  }
}
