package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import java.lang.ref.SoftReference;

@Deprecated
public class BufferRecyclers {
  public static final String SYSTEM_PROPERTY_TRACK_REUSABLE_BUFFERS = "com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers";
  
  private static final ThreadLocalBufferManager _bufferRecyclerTracker;
  
  static {
    boolean trackReusableBuffers = false;
    try {
      trackReusableBuffers = "true".equals(System.getProperty("com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers"));
    } catch (SecurityException securityException) {}
    _bufferRecyclerTracker = trackReusableBuffers ? ThreadLocalBufferManager.instance() : null;
  }
  
  protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef = new ThreadLocal<>();
  
  @Deprecated
  public static BufferRecycler getBufferRecycler() {
    SoftReference<BufferRecycler> ref = _recyclerRef.get();
    BufferRecycler br = (ref == null) ? null : ref.get();
    if (br == null) {
      br = new BufferRecycler();
      if (_bufferRecyclerTracker != null) {
        ref = _bufferRecyclerTracker.wrapAndTrack(br);
      } else {
        ref = new SoftReference<>(br);
      } 
      _recyclerRef.set(ref);
    } 
    return br;
  }
  
  public static int releaseBuffers() {
    if (_bufferRecyclerTracker != null)
      return _bufferRecyclerTracker.releaseBuffers(); 
    return -1;
  }
  
  @Deprecated
  public static JsonStringEncoder getJsonStringEncoder() {
    return JsonStringEncoder.getInstance();
  }
  
  @Deprecated
  public static byte[] encodeAsUTF8(String text) {
    return JsonStringEncoder.getInstance().encodeAsUTF8(text);
  }
  
  @Deprecated
  public static char[] quoteAsJsonText(String rawText) {
    return JsonStringEncoder.getInstance().quoteAsString(rawText);
  }
  
  @Deprecated
  public static void quoteAsJsonText(CharSequence input, StringBuilder output) {
    JsonStringEncoder.getInstance().quoteAsString(input, output);
  }
  
  @Deprecated
  public static byte[] quoteAsJsonUTF8(String rawText) {
    return JsonStringEncoder.getInstance().quoteAsUTF8(rawText);
  }
}
