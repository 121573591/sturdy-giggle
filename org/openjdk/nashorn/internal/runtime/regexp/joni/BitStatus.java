package org.openjdk.nashorn.internal.runtime.regexp.joni;

final class BitStatus {
  public static final int BIT_STATUS_BITS_NUM = 32;
  
  public static int bsClear() {
    return 0;
  }
  
  public static int bsAll() {
    return -1;
  }
  
  public static boolean bsAt(int stats, int n) {
    return (((n < 32) ? (stats & 1 << n) : (stats & 0x1)) != 0);
  }
  
  public static int bsOnAt(int statsp, int n) {
    int stats = statsp;
    if (n < 32) {
      stats |= 1 << n;
    } else {
      stats |= 0x1;
    } 
    return stats;
  }
  
  public static int bsOnOff(int v, int f, boolean negative) {
    return negative ? (v & (f ^ 0xFFFFFFFF)) : (v | f);
  }
}
