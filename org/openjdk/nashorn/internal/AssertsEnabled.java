package org.openjdk.nashorn.internal;

public final class AssertsEnabled {
  private static boolean assertsEnabled = false;
  
  static {
    assert assertsEnabled = true;
  }
  
  public static boolean assertsEnabled() {
    return assertsEnabled;
  }
}
