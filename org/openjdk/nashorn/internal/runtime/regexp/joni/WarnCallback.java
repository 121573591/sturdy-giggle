package org.openjdk.nashorn.internal.runtime.regexp.joni;

public interface WarnCallback {
  public static final WarnCallback DEFAULT = new WarnCallback() {
      public void warn(String message) {
        System.err.println(message);
      }
    };
  
  void warn(String paramString);
}
