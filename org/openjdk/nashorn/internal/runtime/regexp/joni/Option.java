package org.openjdk.nashorn.internal.runtime.regexp.joni;

public class Option {
  public static final int NONE = 0;
  
  public static final int IGNORECASE = 1;
  
  public static final int EXTEND = 2;
  
  public static final int MULTILINE = 4;
  
  public static final int SINGLELINE = 8;
  
  public static final int FIND_LONGEST = 16;
  
  public static final int FIND_NOT_EMPTY = 32;
  
  public static final int NEGATE_SINGLELINE = 64;
  
  public static final int DONT_CAPTURE_GROUP = 128;
  
  public static final int CAPTURE_GROUP = 256;
  
  public static final int NOTBOL = 512;
  
  public static final int NOTEOL = 1024;
  
  public static final int POSIX_REGION = 2048;
  
  public static final int MAXBIT = 4096;
  
  public static final int DEFAULT = 0;
  
  public static String toString(int option) {
    String options = "";
    if (isIgnoreCase(option))
      options = options + "IGNORECASE "; 
    if (isExtend(option))
      options = options + "EXTEND "; 
    if (isMultiline(option))
      options = options + "MULTILINE "; 
    if (isSingleline(option))
      options = options + "SINGLELINE "; 
    if (isFindLongest(option))
      options = options + "FIND_LONGEST "; 
    if (isFindNotEmpty(option))
      options = options + "FIND_NOT_EMPTY  "; 
    if (isNegateSingleline(option))
      options = options + "NEGATE_SINGLELINE "; 
    if (isDontCaptureGroup(option))
      options = options + "DONT_CAPTURE_GROUP "; 
    if (isCaptureGroup(option))
      options = options + "CAPTURE_GROUP "; 
    if (isNotBol(option))
      options = options + "NOTBOL "; 
    if (isNotEol(option))
      options = options + "NOTEOL "; 
    if (isPosixRegion(option))
      options = options + "POSIX_REGION "; 
    return options;
  }
  
  public static boolean isIgnoreCase(int option) {
    return ((option & 0x1) != 0);
  }
  
  public static boolean isExtend(int option) {
    return ((option & 0x2) != 0);
  }
  
  public static boolean isSingleline(int option) {
    return ((option & 0x8) != 0);
  }
  
  public static boolean isMultiline(int option) {
    return ((option & 0x4) != 0);
  }
  
  public static boolean isFindLongest(int option) {
    return ((option & 0x10) != 0);
  }
  
  public static boolean isFindNotEmpty(int option) {
    return ((option & 0x20) != 0);
  }
  
  public static boolean isFindCondition(int option) {
    return ((option & 0x30) != 0);
  }
  
  public static boolean isNegateSingleline(int option) {
    return ((option & 0x40) != 0);
  }
  
  public static boolean isDontCaptureGroup(int option) {
    return ((option & 0x80) != 0);
  }
  
  public static boolean isCaptureGroup(int option) {
    return ((option & 0x100) != 0);
  }
  
  public static boolean isNotBol(int option) {
    return ((option & 0x200) != 0);
  }
  
  public static boolean isNotEol(int option) {
    return ((option & 0x400) != 0);
  }
  
  public static boolean isPosixRegion(int option) {
    return ((option & 0x800) != 0);
  }
  
  public static boolean isDynamic(int option) {
    return false;
  }
}
